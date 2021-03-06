/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.moritzf.alto.v3.parser;

import de.moritzf.alto.v3.visitor.ComposedBlockTypeVisitor;
import de.moritzf.alto.v3.visitor.PageSpaceTypeVisitor;
import de.moritzf.alto.v3.visitor.TextLineVisitor;
import gov.loc.standards.alto.ns_v3_.*;
import org.junit.jupiter.api.Test;

import javax.xml.bind.*;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;


class AltoParserTest {
    @Test
    void testAltoSimpleTestFile() throws JAXBException {
        AltoType altoType = AltoParser.parse(AltoParserTest.class.getResourceAsStream("/alto-simple-testfile.xml"));
        System.out.println(altoType.getDescription());
        assertEquals(altoType.getDescription().getMeasurementUnit().value(), "pixel");

        ComposedBlockType block = (ComposedBlockType) altoType.getLayout().getPage().get(0).getPrintSpace().getShapeAndTextBlockOrIllustration().get(0);
        TextBlockType textBlock = (TextBlockType) block.getTextBlockOrIllustrationOrGraphicalElement().get(0);
        TextBlockType.TextLine textLine = textBlock.getTextLine().get(0);
        assertEquals(((StringType) textLine.getShapeAndStringAndSP().get(0)).getCONTENT(), "Hello");
        assertEquals(((SPType) textLine.getShapeAndStringAndSP().get(1)).getWIDTH(), 3f);
        assertEquals(((StringType) textLine.getShapeAndStringAndSP().get(2)).getCONTENT(), "world");
    }

    @Test
    void testAltoSimpleTestFileWithVisitor() throws JAXBException {
        AltoType altoType = AltoParser.parse(AltoParserTest.class.getResourceAsStream("/alto-simple-testfile.xml"));
        System.out.println(altoType.getDescription());
        assertEquals(altoType.getDescription().getMeasurementUnit().value(), "pixel");

        final ComposedBlockType[] block = new ComposedBlockType[1];
        new PageSpaceTypeVisitor() {

            @Override
            public void handle(IllustrationType obj) {

            }

            @Override
            public void handle(ComposedBlockType obj) {
                assert block[0] == null;
                block[0] = obj;
            }

            @Override
            public void handle(TextBlockType obj) {

            }

            @Override
            public void handle(GraphicalElementType obj) {

            }

            @Override
            public void handle(ShapeType obj) {

            }

            @Override
            public void handleUnknown(Object obj) {

            }
        }.visit(altoType.getLayout().getPage().get(0).getPrintSpace());

        final TextBlockType[] textBlock = new TextBlockType[1];
        new ComposedBlockTypeVisitor() {
            @Override
            public void handle(TextBlockType obj) {
                assert textBlock[0] == null;
                textBlock[0] = obj;
            }

            @Override
            public void handle(IllustrationType obj) {

            }

            @Override
            public void handle(ComposedBlockType obj) {

            }

            @Override
            public void handle(GraphicalElementType obj) {

            }

            @Override
            public void handleUnknown(Object obj) {

            }
        }.visit(block[0]);
        final StringJoiner builder = new StringJoiner(" ");
        new TextLineVisitor() {
            @Override
            public void handle(StringType obj) {
                builder.add(obj.getCONTENT());
            }

            @Override
            public void handle(SPType obj) {

            }

            @Override
            public void handle(ShapeType obj) {

            }

            @Override
            public void handleUnknown(Object obj) {

            }
        }.visit(textBlock[0].getTextLine().get(0));
        assertEquals(builder.toString(), "Hello world");
    }

}
