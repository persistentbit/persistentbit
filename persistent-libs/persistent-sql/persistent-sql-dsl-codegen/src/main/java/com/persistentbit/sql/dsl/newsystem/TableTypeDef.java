package com.persistentbit.sql.dsl.newsystem;

import com.persistentbit.collections.PList;
import com.persistentbit.javacodegen.*;
import com.persistentbit.sql.dsl.expressions.impl.ExprContext;
import com.persistentbit.sql.dsl.tables.AbstractTable;
import com.persistentbit.sql.dsl.tables.Table;
import com.persistentbit.sql.dsl.tables.TableName;
import com.persistentbit.string.UString;

/**
 * TODOC
 *
 * @author petermuys
 * @since 25/12/17
 */
public class TableTypeDef implements TypeDef{

	private final String            catalogName;
	private final String            schemaName;
	private final String            tableName;
	private final PList<TableField> fields;


	public TableTypeDef(String catalogName, String schemaName, String tableName,
						PList<TableField> fields
	) {
		this.catalogName = catalogName;
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.fields = fields;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	private TypeRef getTableRef(CgContext context) {
		return context.createTableTypeRef(catalogName, schemaName, tableName);
	}

	@Override
	public TypeRef getRef(CgContext context) {
		return context.createExprTypeRef(catalogName, schemaName, tableName);
	}

	public TypeRef getJavaRef(CgContext context) {
		return context.createValueTypeRef(catalogName, schemaName, tableName);
	}

	private TypeRef getFactoryRef(CgContext context) {
		return context.createTypeFactoryTypeRef(catalogName, schemaName, tableName);
	}

	public PList<JJavaFile> generate(CgContext context) {

		JJavaFile jfTableClass = new JJavaFile(getTableRef(context).getPackageName())
			.addClass(buildTableClass(context));

		return PList.val(jfTableClass);
	}

	private JClass buildTableClass(CgContext context) {
		TypeRef tableRef = getTableRef(context);
		TypeRef exprRef  = getRef(context);
		TypeRef javaRef  = getJavaRef(context);


		JClass  cls        = new JClass(getTableRef(context).getClassName());
		TypeRef extendsRef = TypeRef.create(AbstractTable.class).withGenerics(tableRef, javaRef);

		cls = cls.addImports(extendsRef.getImports(context));
		cls = cls.addImports(javaRef.getImports(context));


		cls = cls.asFinal().extendsDef("extends " + extendsRef.getClassName());
		//ADD TABLENAME
		cls = cls.addField(
			new JField("_tableName", TableName.class)
				.initValue("new " + TableName.class.getSimpleName()
							   + "(" + sep(esc(catalogName), esc(schemaName), esc(tableName)) + ")")
				.addImport(TableName.class)
		);

		//ADD _ALL
		cls = cls.addField(new JField("_all", exprRef.getClassName()));

		//ADD FIELDS
		for(TableField field : fields) {
			cls = cls.addField(field.createExprField(this, context));
		}

		//CREATE CONSTRUCTORS
		cls = cls.addMethod(
			new JMethod(tableRef.getClassName())
				.withAccessLevel(AccessLevel.Private)
				.addArg(new JArgument(ExprContext.class.getSimpleName(), "context"))
				.addArg(new JArgument("String", "alias"))
				.withCode(pw -> {
					pw.println("super(context, alias);");
					pw.println("this._all = context");
					pw.println("\t.getTypeFactory(" + exprRef.getClassName() + ".class)");
					pw.println("\t.buildTableField(createFullTableNameOrAlias() + \".\",\"\",\"\");");
					for(TableField tf : fields) {
						String jn = tf.getJavaName(this, context);
						pw.println("this." + jn + " = _all." + jn);
					}
				})
		).addImport(ExprContext.class);

		cls = cls.addMethod(
			new JMethod(tableRef.getClassName())
				.withAccessLevel(AccessLevel.Public)
				.addArg(new JArgument(ExprContext.class.getSimpleName(), "context"))
				.withCode(pw -> {
					pw.println("this.context,null);");
				})
		);
		//CREATE TYPECLASS GETTER
		cls = cls.addMethod(
			new JMethod("getTypeClass", "Class<? extends " + Table.class.getSimpleName() + "<" + exprRef
				.getClassName() + ", " + javaRef.getClassName() + ">")
				.withAccessLevel(AccessLevel.Protected)
				.overrides()
				.withCode(pw -> {
					pw.println("return this.getClass();");
				})
				.addImport(JImport.forClass(Table.class))
		);
		//CREATE TableName GETTER
		cls = cls.addMethod(
			new JMethod("getTableName", TableName.class.getSimpleName())
				.withAccessLevel(AccessLevel.Protected)
				.overrides()
				.withCode(pw -> {
					pw.println("return _tableName;");
				})
		);

		//CREATE as
		cls = cls.addMethod(
			new JMethod("as", tableRef.getClassName())
				.withAccessLevel(AccessLevel.Public)
				.addArg(new JArgument("String", "aliasName"))
				.overrides()
				.withCode(pw -> {
					pw.println("return new " + tableRef.getClassName() + "(context, aliasName);");
				})
		);
		//CREATE all
		cls = cls.addMethod(
			new JMethod("all", exprRef.getClassName())
				.withAccessLevel(AccessLevel.Public)
				.overrides()
				.withCode(pw -> {
					pw.println("return _all;");
				})
		);
		return cls;
	}

	private final String esc(String str) {

		return str == null
			? "null"
			: "\"" + UString.escapeToJavaString(str) + "\"";
	}

	private final String sep(Object... val) {
		return sepWith(", ", val);
	}

	private final String sepWith(String sep, Object... val) {
		String res = "";
		for(Object obj : val) {
			if(res.isEmpty() == false) {
				res += sep;
			}
			res += obj.toString();
		}
		return res;
	}
}
