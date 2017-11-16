package com.persistentbit.core;

import com.persistentbit.result.Result;
import com.persistentbit.result.Success;

/**
 * Instance value representing a OK result value.<br>
 *
 * @author Peter Muys
 * @since 9/01/2017
 */
public final class OK {
    /**
     * The global {@link OK} instance
     */
    public static final OK inst   = new OK();
    /**
     * An OK {@link Success} {@link Result}
     */
    public static final Result<OK> result = Result.success(OK.inst);

    private OK() { }

    @Override
    public String toString() {
        return "OK";
    }
}
