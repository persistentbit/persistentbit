package com.persistentbit.json.security;

import com.persistentbit.result.Result;
import com.persistentbit.json.mapping.impl.JJsonException;
import com.persistentbit.json.nodes.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Objects;


/**
 * JJSigning makes it possible to sign a JJNode.<br>
 * This can be used to generate/verify JJNode or to generate String tokens from a JJNode.<br>
 * Example Usage:<br>
 * <pre>{@code
 * JJSigning signing = new JJSigning("YourSecretKey","SHA-256");
 * JJNode signed = signing.sign(unsigned); //signed result as a JJNode
 * String token = signing.signAsString(unsigned); //signed result as a string value
 *
 * Optional<JJNode> verified = signing.unsigned(signed);   //empty when verification failed
 * Optional<JJNode> verifiedToken = signing.unsignedFromString(token); //empty when verification failed
 *
 * }</pre>
 */
public class JJSigning {
    private final char[]   secret;
    private final String hashAlgorithm;


    /**
     * Construct with default "SHA-256" algorithm
     * @param secret    The secret to use
     */
    public JJSigning(String secret){
        this(secret,"SHA-256");
    }

    /**
     *
     * @param secret The secret to use
     * @param hashAlgorithm the hash-algorithm to use. (see {@link MessageDigest})
     */
    public JJSigning(String secret, String hashAlgorithm){
        this.secret = Objects.requireNonNull(secret.toCharArray());
        this.hashAlgorithm = hashAlgorithm;
    }


    /**
     * Sign a JJNode by creating a hash value and
     * wrapping the data and secret in a new json-object with properties "data" and "signed";
     * @param unsigned The JJNode to sign
     * @return A new JJNodeObject with the data and the hash code
     */
    public Result<JJNode> sign(JJNode unsigned){
        return Result.function(unsigned).code(log -> {
            String str = JJPrinter.print(false,unsigned)+"."+new String(secret);
            return sign(str,hashAlgorithm)
                .map(signed -> hashAlgorithm+"."+signed)
                .map(signed -> new JJNodeObject().plus("data",unsigned).plus("signed",new JJNodeString(signed)));

        });

    }

    /**
     * Wrapper  around {@link #sign(JJNode)} that returns just a base64 encoded string of the signed
     * JJNode.<br>
     * Can be used to create a Authentication token.<br>
     * @param unsigned The unsinged JJNode
     * @return Base64 encode string.
     */
    public Result<String> signAsString(JJNode unsigned){
        return Result.function(unsigned).code(log ->
            sign(unsigned)
                .map(us -> JJPrinter.print(false,us).getBytes(Charset.forName("UTF-8")))
                .map(bytes -> Base64.getEncoder().encodeToString(bytes))
        );
    }

    /**
     * Verify and return the Unsinged JJNode object from a signed JJNode object
     * @param signed The signed JJNode
     * @return empty if verification failed, or unsigned version of the JJNode
     * @see #sign(JJNode)
     */
    public Result<JJNode> unsigned(JJNode signed){
        return Result.function(signed).code(log ->
            signed.asObject().flatMap(obj -> {
                String signedSecret     = obj.getValue().get("signed").asString().orElseThrow().getValue();
                int    i                = signedSecret.indexOf('.');
                String algorithm        = signedSecret.substring(0,i);
                String onlySignedSecret = signedSecret.substring(i+1);
                log.info("onlySignedSecret = " + onlySignedSecret);
                String str = JJPrinter.print(false,obj.getValue().get("data"))+"."+new String(secret);
                return sign(str,algorithm)
                    .verify(sstr -> sstr.equals(onlySignedSecret))
                    .map(sstr -> obj.getValue().get("data"));

			})
        );
    }
    /**
     * Verify and return the Unsinged JJNode object from a signed String
     * @param str The signed JJNode as Base64 encode string
     * @return empty if verification failed, or unsigned version of the JJNode
     * @see #signAsString(JJNode)
     */
    public Result<JJNode> unsignedFromString(String str){
        try {
            JJNode node = JJParser.parse(new String(Base64.getDecoder().decode(str),"UTF-8")).orElseThrow();
            return unsigned(node);
        } catch (UnsupportedEncodingException e) {
            throw new JJsonException(e);
        }
    }

    static public Result<String> sign(String data, String algorithm){
        return Result.function(data,algorithm).code(log ->{
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data.getBytes("UTF-8"));
            byte[] mdbytes = md.digest();
            return Result.success(Base64.getEncoder().encodeToString(mdbytes));
        });
    }




}
