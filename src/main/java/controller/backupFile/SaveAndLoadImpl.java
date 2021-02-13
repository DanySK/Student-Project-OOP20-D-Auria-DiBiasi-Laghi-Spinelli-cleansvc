package controller.backupFile;

public class SaveAndLoadImpl implements SaveAndLoad {

    /*
     * Per Silvia:
     * In massima FileReader + BufferedReader / FileWriter + BufferedWriter (fonte: slide 68 Blocco 15 - InputOutput)
     * 
     * public static final String FILE_NALE = "/..path../file.txt";
     * 
     * public static void read throws Exception {
     *      try ( final BufferedReader r = new BufferedReader (new FileReader (FILE_NAME)) ) {
     *          String line = null;
     *          while ( (line = r.readLine()) != null) { 
     *              System.out.println(line)
     *          }
     *      }
     * public static void write throws Exception {
     *      try ( final BufferedWriter w = new BufferedWriter (new FileWriter (FILE_NAME)) ) {
     *          w.write("string");
     *          w.newLine();
     *      }
     * 
     */
    @Override
    public void saveStaff() {
        // TODO Auto-generated method stub
    }

    @Override
    public void loadStaff() {
        // TODO Auto-generated method stub
    }

    @Override
    public void saveClient() {
        // TODO Auto-generated method stub
    }

    @Override
    public void loadClient() {
        // TODO Auto-generated method stub
    }

    @Override
    public void saveProduct() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void loadProduct() {
        // TODO Auto-generated method stub
        
    }
}
