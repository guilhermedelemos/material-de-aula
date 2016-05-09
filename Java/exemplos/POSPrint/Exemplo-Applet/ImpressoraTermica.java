import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ImpressoraTermica {
    private FileWriter fw;
    private PrintWriter pw;
    private String porta; // "LPT1"
    private int qtdeColunas; // 50
    private final char CORTE_TOTAL[] = { (char)27, (char)119 };
    private final char CORTE_PARCIAL[] = { (char)27, (char)109 };
    private final char LINE_FEED = (char)10; // "\r\n" \\ "\n"
    private String linhaHorizontal;
    private String caracterLinhaHorizontal = "-";

    public ImpressoraTermica(String porta) throws IOException {
        super();
        preparar(porta, 40);
    }
    public ImpressoraTermica(String porta, int colunas) throws IOException {
        super();
        preparar(porta, colunas);
    }
    private void preparar(String porta, int qtdeColunas) throws IOException {
        this.porta       = porta;
        this.qtdeColunas = qtdeColunas;
        this.fw          = new FileWriter(this.porta);
        this.pw          = new PrintWriter(this.fw);
        StringBuilder linha = new StringBuilder("");
        for(int i=0; i<qtdeColunas; i++) {
            linha.append(this.caracterLinhaHorizontal);
        }
        this.linhaHorizontal = linha.toString();
    }
    private void fecharComunicacaoImpressora() throws IOException {
        this.pw.close();
        this.fw.close();
    }
    public void acionarCorteParcial() throws IOException {
        pw.print(this.CORTE_PARCIAL[0] + "" + this.CORTE_PARCIAL[1]);
    }
    public void acionarCorteTotal() throws IOException {
        pw.print(this.CORTE_TOTAL[0]+""+this.CORTE_TOTAL[1]);
    }
    public void adicionarLinhaEmBranco() throws IOException {
        pw.print(this.LINE_FEED);
    }
    public void adicionarLinha(String dados) throws IOException {
        pw.print(dados);
    }
    public void adicionarLinhaComQuebra(String dados) throws IOException {
        pw.print(dados);
        adicionarLinhaEmBranco();
    }
    public void finalizar() throws IOException {
        fecharComunicacaoImpressora();
    }
    public void print(String s) throws IOException {
        adicionarLinha(s);
    }
    public void println(String s) throws IOException {
        adicionarLinhaComQuebra(s);
    }
    public void adicionarLinhaHorizontal() throws IOException {
        adicionarLinhaComQuebra(this.linhaHorizontal);
    }
    public static void main(String[] args) {
        try {
            ImpressoraTermica it = new ImpressoraTermica("LPT1");
            it.adicionarLinhaComQuebra("Teste 1");
            it.adicionarLinha("Teste 2");
            it.adicionarLinhaEmBranco();
            it.acionarCorteParcial();
            it.finalizar();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}