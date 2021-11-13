package de.moritzf.alto.v3.visitor;

import gov.loc.standards.alto.ns_v3_.*;

/**
 * Visitor for {@link PageSpaceType}.
 *
 * Call {@link PageSpaceTypeVisitor#visit(PageSpaceType)} to visit all
 * elements in {@link PageSpaceType#getShapeAndTextBlockOrIllustration()}.
 */
public interface PageSpaceTypeVisitor {

    /**
     * Visits all elements in {@link PageSpaceType#getShapeAndTextBlockOrIllustration()}.
     */
    default void visit(PageSpaceType pageSpaceType) {
        for (Object obj : pageSpaceType.getShapeAndTextBlockOrIllustration()) {
            if (obj instanceof IllustrationType) {
                handle((IllustrationType) obj);
            } else if (obj instanceof GraphicalElementType) {
                handle((GraphicalElementType) obj);
            } else if (obj instanceof ComposedBlockType) {
                handle((ComposedBlockType) obj);
            } else if (obj instanceof ShapeType) {
                handle((ShapeType) obj);
            } else if (obj instanceof TextBlockType) {
                handle((TextBlockType) obj);
            } else {
                handleUnknown(obj);
            }
        }
    }

    void handle(IllustrationType obj);

    void handle(GraphicalElementType obj);

    void handle(ComposedBlockType obj);

    void handle(TextBlockType obj);

    void handle(ShapeType obj);

    void handleUnknown(Object obj);
}
