package ua.training.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import ua.training.model.entities.VectorGraphicFile;
import ua.training.model.entities.FileExtensionsRaster;
import ua.training.model.entities.FileExtensionsVector;
import ua.training.model.entities.GraphicFile;
import ua.training.model.entities.RasterGraphicFile;

public class SlideShowGraphicFilesCollection {
    private ArrayList<GraphicFile> graphicFilesList;

    public SlideShowGraphicFilesCollection(String pathToFolder)  {
        this.graphicFilesList = new ArrayList<GraphicFile>(); 

    	if("".equals(pathToFolder)) {
    		return;
    	}
    	
		File myFolder = new File(pathToFolder);
		if(!myFolder.exists()) {
			//TODO throw exception in future releases
			return;
		}
		
		File[] files = myFolder.listFiles();
		
		if(files.length == 0) {
			return;
		}
		
		ArrayList<GraphicFile> availableGraphicFilesList = new ArrayList<GraphicFile>();
		
		for(File file: files) {
			
			GraphicFile graphicFile = null;
			
			String fileExtensionUpperCase = GraphicFile.getFileExtension(file.getAbsolutePath()).toUpperCase();
			
			if(FileExtensionsVector.getValueOf(fileExtensionUpperCase) != null) {
				graphicFile = new VectorGraphicFile(file);
			} else if(FileExtensionsRaster.getValueOf(fileExtensionUpperCase) != null) {
				graphicFile = new RasterGraphicFile(file);
			} else {
				//simply ignore unknown file extensions
				continue;
			}
			
			availableGraphicFilesList.add(graphicFile);
		}
    	
    	
        this.graphicFilesList.addAll(availableGraphicFilesList); 
    }

	public SlideShowGraphicFilesCollection(ArrayList<GraphicFile> newGraphicFilesList) {
        this.graphicFilesList = new ArrayList<GraphicFile>(newGraphicFilesList); 
	}

	public ArrayList<GraphicFile> getGraphicFilesList() {
		return graphicFilesList;
	}

    public SlideShowGraphicFilesCollection filterGraphicFilesBySize(long minSize, long maxSize) {
        if((minSize < 0) || (maxSize < 0)) {
        	//TODO maybe throw exception "invalid parameters" in future releases
        	return null;
        }
    	
        ArrayList<GraphicFile> newGraphicFilesList;
        if((minSize == 0) && (maxSize == 0)) {
        	newGraphicFilesList = graphicFilesList;
        } else {
        	newGraphicFilesList = new ArrayList<GraphicFile>();
        	for(GraphicFile graphicFile: graphicFilesList) {
        		long fileSize = graphicFile.getFileSize();
	        	if ((minSize > 0) && (maxSize == 0)) {
	        		if(fileSize >= minSize) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            } else if ((minSize == 0) && (maxSize > 0)) {
	        		if(fileSize <= maxSize) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            } else {
	        		if((minSize <= fileSize) && (fileSize <= maxSize)) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            }
        	}
        }
        
        return new SlideShowGraphicFilesCollection(newGraphicFilesList);
    }

    public SlideShowGraphicFilesCollection filterGraphicFilesByLastModified(long minDateModified, long maxDateModified) {
        if((minDateModified < 0) || (maxDateModified < 0)) {
        	//TODO maybe throw exception "invalid parameters" in future releases
        	return null;
        }
    	
        ArrayList<GraphicFile> newGraphicFilesList;
        if((minDateModified == 0) && (maxDateModified == 0)) {
        	newGraphicFilesList = graphicFilesList;
        } else {
        	newGraphicFilesList = new ArrayList<GraphicFile>();
        	for(GraphicFile graphicFile: graphicFilesList) {
        		long fileLastModified = graphicFile.getLastModified();
	        	if ((minDateModified > 0) && (maxDateModified == 0)) {
	        		if(fileLastModified >= minDateModified) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            } else if ((minDateModified == 0) && (maxDateModified > 0)) {
	        		if(fileLastModified <= maxDateModified) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            } else {
	        		if((minDateModified <= fileLastModified) && (fileLastModified <= maxDateModified)) {
	        			newGraphicFilesList.add(graphicFile);
	        		}
	            }
        	}
        }
        
        return new SlideShowGraphicFilesCollection(newGraphicFilesList);
    }

    public SlideShowGraphicFilesCollection filterGraphicFilesByTAGs(String... tags) {
        ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>();
        
        if(tags.length > 0) {
        	for(GraphicFile graphicFile: graphicFilesList) {
        		for(String tag: tags) {
        			if(graphicFile.hasTag(tag)) {
        				newGraphicFilesList.add(graphicFile);
	            		break;
        			}
        		}
        	}
        }
        
        return new SlideShowGraphicFilesCollection(newGraphicFilesList);
    }
    
    public void sortGraphicFilesBySize() {
    	TreeMap<Integer, Long> entries = new TreeMap<Integer, Long>();
    	
    	int i = 0;
    	for(GraphicFile graphicFile: graphicFilesList) {
   			entries.put(i, graphicFile.getFileSize());
    		i++;
    	}
    	
    	TreeMap<Integer, Long> sortedTreeMap = sortByValues(entries);
    	
    	ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>();
    	
    	for(Map.Entry<Integer, Long> entry: sortedTreeMap.entrySet()) {
    		newGraphicFilesList.add((GraphicFile) graphicFilesList.get(entry.getKey()));
    	}
    	
    	graphicFilesList = newGraphicFilesList;
    }

    public void sortGraphicFilesByLastModified() {
    	TreeMap<Integer, Long> entries = new TreeMap<Integer, Long>();
    	
    	int i = 0;
    	for(GraphicFile graphicFile: graphicFilesList) {
   			entries.put(i, graphicFile.getLastModified());
    		i++;
    	}
    	
    	TreeMap<Integer, Long> sortedTreeMap = sortByValues(entries);
    	
    	ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>();
    	
    	for(Map.Entry<Integer, Long> entry: sortedTreeMap.entrySet()) {
    		newGraphicFilesList.add((GraphicFile) graphicFilesList.get(entry.getKey()));
    	}
    	
    	graphicFilesList = newGraphicFilesList;
    }

    //note: sort by first TAG
    public void sortGraphicFilesByTAGs() {
    	TreeMap<Integer, String> entries = new TreeMap<Integer, String>();
    	
    	int i = 0;
    	for(GraphicFile graphicFile: graphicFilesList) {
   			entries.put(i, graphicFile.getTagByIndex(0));
    		i++;
    	}
    	
    	TreeMap<Integer, String> sortedTreeMap = sortByValues(entries);
    	
    	ArrayList<GraphicFile> newGraphicFilesList = new ArrayList<GraphicFile>();
    	
    	for(Map.Entry<Integer, String> entry: sortedTreeMap.entrySet()) {
    		newGraphicFilesList.add((GraphicFile) graphicFilesList.get(entry.getKey()));
    	}
    	
    	graphicFilesList = newGraphicFilesList;
    }
    
    public long slideShowFilesTotalSize() {
        long res = 0;
    	
    	for(GraphicFile graphicFile: graphicFilesList) {
    		res += graphicFile.getFileSize();
    	}
        
        return res;
    }
    
	private static <K, V extends Comparable<V>> TreeMap<K, V> sortByValues(final TreeMap<K, V> map) {
		Comparator<K> valueComparator = new Comparator<K>() {
			public int compare(K k1, K k2) {
				int compare = map.get(k1).compareTo(map.get(k2));
				if (compare == 0) {
					return 1;
				} else {
					return compare;
				}
			}
		};

		TreeMap<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
		sortedByValues.putAll(map);
		return sortedByValues;
	}
}
