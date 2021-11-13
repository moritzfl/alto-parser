package de.moritzf.alto.v3.visitor;

import gov.loc.standards.alto.ns_v3_.*;

/**
 * Visitor for {@link ComposedBlockType}.
 *
 * Call {@link ComposedBlockTypeVisitor#visit(ComposedBlockType)} to visit all
 * elements in {@link ComposedBlockType#getTextBlockOrIllustrationOrGraphicalElement()}.
 */
public interface ComposedBlockTypeVisitor {

    /**
     * Visits all elements in {@link ComposedBlockType#getTextBlockOrIllustrationOrGraphicalElement()}.
     */
    default void visit(ComposedBlockType block) {
        for (BlockType obj : block.getTextBlockOrIllustrationOrGraphicalElement()) {
            if (obj instanceof IllustrationType) {
                handle((IllustrationType) obj);
            } else if (obj instanceof TextBlockType) {
                handle((TextBlockType) obj);
            } else if (obj instanceof GraphicalElementType) {
                handle((GraphicalElementType) obj);
            } else if (obj instanceof ComposedBlockType) {
                handle((ComposedBlockType) obj);
            } else {
                handleUnknown(obj);
            }
        }
    }

    void handle(TextBlockType obj);

    void handle(IllustrationType obj);

    void handle(ComposedBlockType obj);

    void handle(GraphicalElementType obj);

    void handleUnknown(Object obj);
}
