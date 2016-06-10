package org.cytoscape.netInfo.writer;

import java.io.OutputStream;
import org.cytoscape.io.CyFileFilter;
import org.cytoscape.io.write.CyNetworkViewWriterFactory;
import org.cytoscape.io.write.CyWriter;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.view.model.CyNetworkView;

/**
 * @author SrikanthB
 */ 

public class NetInfoWriterFactory implements CyNetworkViewWriterFactory {
    private final CyFileFilter filter;
    private final CyNetworkManager cyNetworkManager;
    
    public NetInfoWriterFactory(final CyFileFilter filter, final CyNetworkManager cyNetworkManager) {
        this.filter = filter;
        this.cyNetworkManager = cyNetworkManager;
    }
    
    @Override
    public CyWriter createWriter(OutputStream outputStream, CyNetwork network) {
            return new NetInfoWriter(outputStream, network, cyNetworkManager);
    }

    @Override
    public CyFileFilter getFileFilter() {
            return filter;
    }

    @Override
    public CyWriter createWriter(OutputStream out, CyNetworkView cnv) {
        // TODO  : update this
        //return new AdjNetworkViewWriter(out, cnv);
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}