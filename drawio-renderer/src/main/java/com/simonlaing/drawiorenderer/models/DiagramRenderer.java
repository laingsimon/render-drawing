package com.simonlaing.drawiorenderer.models;

import com.mxgraph.io.mxCodec;
import com.mxgraph.view.mxGraph;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.zip.DataFormatException;

@Service
public class DiagramRenderer {
    private final DiagramDecoder decoder;

    public DiagramRenderer(DiagramDecoder decoder) {
        this.decoder = decoder;
    }

    public Object render(String xmlString, RenderFormat format) throws DataFormatException, IOException {
        Document xml = decoder.getDiagramData(xmlString);

        mxCodec codec = new mxCodec(xml);
        mxGraph graph = new mxGraph();

        codec.decode(xml.getDocumentElement(), graph.getModel());

        return format.render(graph);
    }
}
