import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
public class CEFOFXFix {
    public void fix(String pathToCefOfxFile) {
        File arquivo = new File(pathToCefOfxFile);
        this.fix(arquivo);
    }
    public void fix(File arquivo) {
        System.out.println(arquivo.getAbsolutePath());
        try {
            FileWriter fw = new FileWriter(arquivo.getName() + ".new");
            BufferedWriter bw = new BufferedWriter(fw);
        
            int acumulador = 1;
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha = null;
            String dataRegistro = "";
            while( ( linha = br.readLine() ) != null ) {
                if(linha.indexOf("<DTPOSTED>") > 0) {
                    dataRegistro = linha.substring( linha.indexOf(">")+1, linha.indexOf(">")+9);
                }
                if(linha.indexOf("<FITID>") > 0) {
                    linha = "      <FITID>" + dataRegistro + acumulador + "</FITID>";
                    dataRegistro = "";
                }
                System.out.println(acumulador + " " + linha);
                acumulador++;
                
                bw.write(linha);
                bw.newLine();
            }
            br.close();
            fr.close();
            
            bw.close();
            fr.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        if(args.length != 1) {
            System.out.println("Caminho para o arquivo OFX esperado!");
            return;
        }
        File arquivo = new File(args[0]);
        if(!arquivo.exists() || !arquivo.isFile()) {
            System.out.println("Caminho para o arquivo OFX inválido!");
            return;
        }
        System.out.println("Fix OFX CEF - INICIO");
        CEFOFXFix obj = new CEFOFXFix();
        obj.fix(arquivo);
        System.out.println("Concluido");
    }
}
