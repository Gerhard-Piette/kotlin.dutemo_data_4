package com.volupism.dutemo_data_4

import com.volupism.ds2_0.Ds2

/**
 * Class with data for a namespace.
 *
 * Notably for finding tokens by name.
 */
class Namespace {

    /**
     *  Data from the Key class.
     *  - "" indicates Page namespace.
     *  - :a indicates a :a namespace.
     */
    var key: String = ""

    /**
     * input = Name in a statement.
     *
     * output = List of tokens linked to the name. A list of tokens because of non-unique names for agendas.
     */
    var statement_d: LinkedHashMap<String, List<Token>> = LinkedHashMap()

    /**
     * Position of the token where the namespace begins.
     */
    var ini: Int = 0

    /**
     * Position of the token where the namespace ends.
     */
    var fini: Int = 0

    /**
     * The low namespace that contains this namespace.
     *
     * null indicates that this namespace is the namespace of a Page.
     */
    var low_namespace: Namespace? = null

    /**
     * List of higher namespaces contained in this namespace.
     *
     * null indicates absence of higher namespaces.
     */
    var high_namespace_ds: Ds2<Namespace>? = null

}