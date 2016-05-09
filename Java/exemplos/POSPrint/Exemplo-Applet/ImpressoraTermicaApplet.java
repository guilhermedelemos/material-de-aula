/* VEJA COMO ASSINAR UM JAR COM CERTIFICADO DIGITAL
 * http://support.sas.com/rnd/appdev/V30/tech/signing/RSASigning.htm
 * ATENÇÃO, versão do compilador (jdk) e da maquina virtual (jre) devem se a mesma,
 * inclusive o plugin do navegador. Testado com Java 8.
 */
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.AccessControlContext;
import javax.print.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.applet.*;
import java.awt.*;

public class ImpressoraTermicaApplet extends Applet {
    private AccessControlContext acc;
    private final int CMD_IMPRIMIR       = 1;
    private final int CMD_CORTAR_PARCIAL = 2;
    private final int CMD_CORTAR_TOTAL   = 3;

    public void paint(Graphics g) {
        if(acc==null) {
            acc = AccessController.getContext();
        }
    }
    private void imprimir(String s, int comando) {
        if(acc==null) {
            System.err.println("Não é possível utilizar a impressora devido a configurações de segurança de seu navegador!");
            return;
        }
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    ImpressoraTermica it = new ImpressoraTermica("LPT1");

                    switch(comando) {
                        case CMD_IMPRIMIR:
                            it.adicionarLinhaComQuebra(s);
                            break;
                        case CMD_CORTAR_PARCIAL:
                            it.acionarCorteParcial();
                            break;
                        case CMD_CORTAR_TOTAL:
                            it.acionarCorteTotal();
                            break;
                    }
                    it.finalizar();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, acc);
    }
    public void imprimir(String s) {
        try {
            imprimir(s, CMD_IMPRIMIR);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void cortarParcial() {
        try {
            imprimir("", CMD_CORTAR_PARCIAL);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void cortarTotal() {
        try {
            imprimir("", CMD_CORTAR_TOTAL);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}