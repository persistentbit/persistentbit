package com.persistentbit.sql.dsl.codegen.importer;

import com.persistentbit.code.annotations.Nullable;
import com.persistentbit.javacodegen.JClass;
import com.persistentbit.javacodegen.annotations.CaseClass;
import com.persistentbit.javacodegen.annotations.Generated;
import com.persistentbit.javacodegen.annotations.NoBuilder;
import com.persistentbit.printable.PrintableText;
import com.persistentbit.string.UString;

import java.util.Objects;

/**
 * TODOC
 *
 * @author petermuys
 * @since 27/12/17
 */
@CaseClass
@NoBuilder
public class DbGenContext{

	private final TypeRef       typeRefDb;
	private final JClass        cls;
	private final String        exprContext;
	private final PrintableText initCode;
	
	
	@Generated
	public DbGenContext(TypeRef typeRefDb, JClass cls, String exprContext, PrintableText initCode) {
		this.typeRefDb = Objects.requireNonNull(typeRefDb, "typeRefDb can not be null");
		this.cls = Objects.requireNonNull(cls, "cls can not be null");
		this.exprContext = Objects.requireNonNull(exprContext, "exprContext can not be null");
		this.initCode = Objects.requireNonNull(initCode, "initCode can not be null");
	}

	public DbGenContext addInitCode(PrintableText pt) {
		return withInitCode(pw -> {
			initCode.print(pw);
			pt.print(pw);
		});
	}
	/**
	 * Get the value of field {@link #typeRefDb}.<br>
	 * @return {@link #typeRefDb}
	 */
	@Generated
	public TypeRef getTypeRefDb() {
		return this.typeRefDb;
	}
	/**
	 * Create a copy of this DbGenContext object with a new value for field {@link #typeRefDb}.<br>
	 * @param typeRefDb The new value for field {@link #typeRefDb}
	 * @return A new instance of {@link DbGenContext}
	 */
	@Generated
	public DbGenContext withTypeRefDb(TypeRef typeRefDb) {
		return new DbGenContext(typeRefDb, cls, exprContext, initCode);
	}
	/**
	 * Get the value of field {@link #cls}.<br>
	 * @return {@link #cls}
	 */
	@Generated
	public JClass getCls() {
		return this.cls;
	}
	/**
	 * Create a copy of this DbGenContext object with a new value for field {@link #cls}.<br>
	 * @param cls The new value for field {@link #cls}
	 * @return A new instance of {@link DbGenContext}
	 */
	@Generated
	public DbGenContext withCls(JClass cls) {
		return new DbGenContext(typeRefDb, cls, exprContext, initCode);
	}
	/**
	 * Get the value of field {@link #exprContext}.<br>
	 * @return {@link #exprContext}
	 */
	@Generated
	public String getExprContext() {
		return this.exprContext;
	}
	/**
	 * Create a copy of this DbGenContext object with a new value for field {@link #exprContext}.<br>
	 * @param exprContext The new value for field {@link #exprContext}
	 * @return A new instance of {@link DbGenContext}
	 */
	@Generated
	public DbGenContext withExprContext(String exprContext) {
		return new DbGenContext(typeRefDb, cls, exprContext, initCode);
	}
	/**
	 * Get the value of field {@link #initCode}.<br>
	 * @return {@link #initCode}
	 */
	@Generated
	public PrintableText getInitCode() {
		return this.initCode;
	}
	/**
	 * Create a copy of this DbGenContext object with a new value for field {@link #initCode}.<br>
	 * @param initCode The new value for field {@link #initCode}
	 * @return A new instance of {@link DbGenContext}
	 */
	@Generated
	public DbGenContext withInitCode(PrintableText initCode) {
		return new DbGenContext(typeRefDb, cls, exprContext, initCode);
	}
	@Generated
	@Override
	public boolean equals(@Nullable Object o) {
		if(this == o) return true;
		if(o instanceof DbGenContext == false) return false;
		DbGenContext obj = (DbGenContext) o;
		if(!typeRefDb.equals(obj.typeRefDb)) return false;
		if(!cls.equals(obj.cls)) return false;
		if(!exprContext.equals(obj.exprContext)) return false;
		if(!initCode.equals(obj.initCode)) return false;
		return true;
	}
	@Generated
	@Override
	public int hashCode() {
		int result;
		result = (this.typeRefDb != null ? this.typeRefDb.hashCode() : 0);
		result = 31 * result + (this.cls != null ? this.cls.hashCode() : 0);
		result = 31 * result + (this.exprContext != null ? this.exprContext.hashCode() : 0);
		result = 31 * result + (this.initCode != null ? this.initCode.hashCode() : 0);
		return result;
	}
	@Generated
	@Override
	public String toString() {
		return "DbGenContext[" +
			"typeRefDb=" + typeRefDb +
			", cls=" + cls +
			", exprContext=" + (exprContext == null ? "null" : '\"' + UString
			.present(UString.escapeToJavaString(exprContext), 32, "...") + '\"') +
			", initCode=" + initCode + 
			']';
	}
}
