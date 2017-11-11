package com.persistentbit.json.mapping.impl;


import com.persistentbit.json.mapping.JJWriter;
import com.persistentbit.json.nodes.JJNode;

/**
 * User: petermuys
 * Date: 24/10/15
 * Time: 18:30
 */
@FunctionalInterface
public interface JJObjectWriter{

    JJNode write(Object value, JJWriter masterWriter);
}