package de.moritzf.alto.v3.visitor;

import gov.loc.standards.alto.ns_v3_.*;

/**
 * Visitor for {@link TextBlockType.TextLine}.
 *
 * Call {@link TextLineVisitor#visit(TextBlockType.TextLine)} to visit all
 * elements in {@link TextBlockType.TextLine#getShapeAndStringAndSP()}.
 */
public interface TextLineVisitor {

    /**
     * Visits all elements in {@link TextBlockType.TextLine#getShapeAndStringAndSP()}.
     */
    default void visit(TextBlockType.TextLine pageSpaceType) {
        for (Object obj : pageSpaceType.getShapeAndStringAndSP()) {
            if (obj instanceof ShapeType) {
                handle((ShapeType) obj);
            } else if (obj instanceof StringType) {
                handle((StringType) obj);
            } else if (obj instanceof SPType) {
                handle((SPType) obj);
            } else {
                handleUnknown(obj);
            }
        }
    }

    void handle(StringType obj);

    void handle(SPType obj);

    void handle(ShapeType obj);

    void handleUnknown(Object obj);
}