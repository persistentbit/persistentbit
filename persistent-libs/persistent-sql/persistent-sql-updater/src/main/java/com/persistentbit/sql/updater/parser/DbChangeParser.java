package com.persistentbit.sql.updater.parser;

import com.persistentbit.collections.PSet;
import com.persistentbit.parser.Parser;
import com.persistentbit.parser.Scan;
import com.persistentbit.string.withprops.Text;
import com.persistentbit.tuples.Tuple3;

import static com.persistentbit.parser.Parser.*;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class DbChangeParser {

	static private Parser<String> sp =
		zeroOrMore(Scan.whiteSpaceAndNewLine
			.or(Scan.lineComment("//"))
			.or(Scan.blockComment("/*","*/"))
		).map(l -> l.toString(""));

	static private Parser<String> key(String keyword){
		return sp.skipAnd(Scan.term(keyword)).and(sp).map(t -> t._1).info(v -> "key(" + v + ")");
	}

	static private Parser<String> name() {
		return Scan.stringLiteral("\"",false);
	}

	static private Parser<If> ifStatement(){
		return toDo("ifStatement");
	}

	static private Parser<Let> letStatement(){
		return toDo("letStatement");
	}

	static private Parser<Include> includeStatement() {
		return toDo("includeStatement");

	}

	static private Parser<Sql> sqlStatement(){
		return sqlStatement(";");
	}

	static private final String kwChange = "#change";
	static private final String kwIf = "#if";
	static private final String kwLet = "#let";
	static private final String kwInclude = "#include";
	static private final PSet<String> statementKeyWords = PSet.val(
		kwChange,kwIf,kwLet,kwInclude
	);
	static private boolean containsKeyWords(String str){
		return statementKeyWords.contains(str);
	}


	static private Parser<Sql>	sqlStatement(String delimiter) {
		return
			Scan.endsWith(delimiter)
			.map(str -> str.substring(0,str.length()-delimiter.length()))
			.verify("Delimiter " + delimiter + " missing.",
					str -> containsKeyWords(str))
			.map(str -> Text.strToText(str))
			.map(txt -> new Sql(txt))

		;

	}

	static private Parser<Statement> singleStatement() {
		return Parser.orOf(
			ifStatement(),
			letStatement(),
			includeStatement(),
			not("Expected sql",key("change")).skipAnd(sqlStatement())
		);
	}

	static private Parser<Statement> statement(){
		return zeroOrMore(singleStatement())
			.map(l -> {
				if(l.size() == 1){
					return l.head();
				}
				return new StatementList(l);
			});
	}

	static private Parser<Change> change(){
		return source -> key(kwChange)
			.info(v -> "Start Parsing change ")
			.skipAnd(key("always").optional())
			.and(name().withPos())
			.and(key("by").skipAnd(name()))
			.map(t -> Tuple3.of(t._1._1,t._1._2,t._2) )
			.and(statement())
			.map(t -> new Change(t._1._2.pos,t._1._1.isPresent(),t._1._2.value,t._1._3,t._2))
			.info(v -> "Got a " + v)
			.parse(source);
	}

	static public Parser<ChangeSet>  parseChangeSet() {
		return zeroOrMore(change().skip(sp))
			.andEof()
			.map(cl -> new ChangeSet(cl));
	}

}
