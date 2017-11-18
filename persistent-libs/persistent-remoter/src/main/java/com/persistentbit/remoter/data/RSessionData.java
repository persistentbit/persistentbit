package com.persistentbit.remoter.data;


import com.persistentbit.json.security.JJSigning;
import com.persistentbit.remoter.RServer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Session Data String with an expiration date that is signed.<br>
 * Is used to store session data in a Remote Call result {@link RCallResult}. <br>
 *
 * @author Peter Muys
 * @since 18/09/16
 * @see RCallResult
 * @see RServer#call(RCall)
 */
public class RSessionData{

    public String data;
    public LocalDateTime validUntil;
    public String signature;

    /**
     * @param data       The Data string representing the session data.
     * @param validUntil The time this session data is valid
     * @param signature  The signature of this structure, normally signed by the service implementation secret
     */
    public RSessionData(String data, LocalDateTime validUntil, String signature) {
        this.data = data;
        this.validUntil = validUntil;
        this.signature = signature;
    }
    public RSessionData(String data, LocalDateTime validUntil) {
        this(data,validUntil,null);
    }

    /**
     * Create a signed version of this session data
     * @param secret The secret, normally coming from the {@link RServer}
     * @return The signed version of this instance.
     */
    public RSessionData    signed(String secret){
        String sig = JJSigning.sign(this.data + this.validUntil.format(DateTimeFormatter.ISO_DATE_TIME) + secret,"SHA-256").orElseThrow();
        return new RSessionData(data,validUntil,sig);
    }

    /**
     * Check if the signature is correct for the given secret.
     *
     * @param secret The secret, normally coming from the {@link RServer}
     *
     * @return true if the signature is correct
     */
    public boolean verifySignature(String secret){
        RSessionData signed = this.signed(secret);
        return signed.signature.equals(this.signature);
    }

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		RSessionData that = (RSessionData) o;

		if(data != null ? !data.equals(that.data) : that.data != null) return false;
		if(validUntil != null ? !validUntil.equals(that.validUntil) : that.validUntil != null) return false;
		return signature != null ? signature.equals(that.signature) : that.signature == null;
	}

	@Override
	public int hashCode() {
		int result = data != null ? data.hashCode() : 0;
		result = 31 * result + (validUntil != null ? validUntil.hashCode() : 0);
		result = 31 * result + (signature != null ? signature.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "RSessionData{" +
			"data='" + data + '\'' +
			", validUntil=" + validUntil +
			'}';
	}


}
