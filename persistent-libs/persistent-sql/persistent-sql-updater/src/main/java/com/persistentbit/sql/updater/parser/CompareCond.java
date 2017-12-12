package com.persistentbit.sql.updater.parser;

import com.persistentbit.result.Result;
import com.persistentbit.utils.exceptions.ToDo;

/**
 * TODOC
 *
 * @author petermuys
 * @since 12/12/17
 */
public class CompareCond implements Cond{



	public enum BinOp{
		equal,notequal
	}

	private final Text left;
	private final BinOp op;
	private final Text right;

	public CompareCond(Text left, BinOp op, Text right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}

	@Override
	public Result<Boolean> run(UpdateContext context) {
		String left = this.left.toString(context);
		String right = this.right.toString(context);
		switch(op){
			case equal:
				return Result.success(left.equals(right));
			case notequal:
				return Result.success(left.equals(right) == false);
				default:
					throw new ToDo(op.toString());
		}
	}
}
