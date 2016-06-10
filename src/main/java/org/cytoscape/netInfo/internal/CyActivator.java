package org.cytoscape.netInfo.internal;

import java.util.Properties;

import org.cytoscape.service.util.AbstractCyActivator;
import org.cytoscape.app.CyAppAdapter;
import org.cytoscape.io.BasicCyFileFilter;
import org.cytoscape.io.DataCategory;
import org.cytoscape.io.util.StreamUtil;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.netInfo.writer.NetInfoWriterFactory;
import static org.cytoscape.work.ServiceProperties.ID;

import org.osgi.framework.BundleContext;

/**
 * @author SrikanthB
 */ 

public class CyActivator extends AbstractCyActivator {
    private static CyAppAdapter appAdapter;
    public static final String FILE_EXTENSION = "csv";
    
    public CyActivator() {
            super();
    }
    
    @Override
    public void start(BundleContext bc) {
            final StreamUtil streamUtil = getService(bc, StreamUtil.class);
            final BasicCyFileFilter netInfoFilter = new BasicCyFileFilter(new String[] {FILE_EXTENSION},
                            new String[] { "application" }, "Networks and their sizes information", DataCategory.NETWORK, streamUtil);
            // get services for readers
            this.appAdapter = getService(bc, CyAppAdapter.class);
            final CyNetworkManager cyNetworkManager = getService(bc, CyNetworkManager.class);
            final NetInfoWriterFactory writerFactory = new NetInfoWriterFactory(netInfoFilter, cyNetworkManager);
            final Properties adjWriterFactoryProperties = new Properties();
            adjWriterFactoryProperties.put(ID, "adjWriterFactory");
            registerAllServices(bc, writerFactory, adjWriterFactoryProperties);
    }
    
    public static CyAppAdapter getCyAppAdapter(){
        return appAdapter;
    }
}
