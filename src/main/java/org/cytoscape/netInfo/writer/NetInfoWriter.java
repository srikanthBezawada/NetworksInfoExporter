package org.cytoscape.netInfo.writer;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Set;
import org.cytoscape.io.write.CyWriter;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.task.AbstractNetworkTask;
import org.cytoscape.work.TaskMonitor;

/**
 * @author SrikanthB
 */ 

public class NetInfoWriter extends AbstractNetworkTask implements CyWriter{
    private final OutputStream outputStream;
    private final CyNetwork network;
    final CyNetworkManager cyNetworkManager;
    public static final String DELIMETER = ",";
    
    public NetInfoWriter(final OutputStream outputStream, final CyNetwork network, final CyNetworkManager cyNetworkManager) {
        super(network);
        this.outputStream = outputStream;	
        this.network = network;
        this.cyNetworkManager = cyNetworkManager;
    }
    
    @Override
	public void run(TaskMonitor taskMonitor) throws Exception {
		if (taskMonitor != null) {
			taskMonitor.setTitle("Networks Information");
			taskMonitor.setStatusMessage("Writing networks information into the output file...");
			taskMonitor.setProgress(-1.0);
		}
                
                Set<CyNetwork> networks = cyNetworkManager.getNetworkSet();
                OutputStreamWriter osWriter = new OutputStreamWriter(outputStream, EncodingUtil.getEncoder()); 
                BufferedWriter bWriter = new BufferedWriter(osWriter);
                bWriter.write("network-name");
                bWriter.write(DELIMETER);
                bWriter.write("no-of-nodes");
                bWriter.write(DELIMETER);
                bWriter.write("no-of-edges");
                bWriter.newLine();
                for(CyNetwork n: networks) {
                    bWriter.write(n.getRow(n).get(CyNetwork.NAME, String.class));
                    bWriter.write(DELIMETER);
                    bWriter.write(Integer.toString(n.getNodeCount()));
                    bWriter.write(DELIMETER);
                    bWriter.write(Integer.toString(n.getEdgeCount()));
                    bWriter.newLine();
                }
                bWriter.close();        
                osWriter.close();
                outputStream.close();
                

		if (taskMonitor != null) {
                        taskMonitor.setTitle("Successfully written to the file!");
			taskMonitor.setStatusMessage("Success.");
			taskMonitor.setProgress(1.0);
		}
                
	}
        
}
