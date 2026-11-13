import java.util.ArrayList;
import java.util.List;

public class SetupInstaller {
    public static void main(String[] args) throws Exception {
        System.out.println("========== SETUP INSTALLER ==========\n");

        System.out.println("========== Atualizando as fontes do winget ==========");
        executar("winget source reset --force");
        executar("winget source update");

        List<String> lista = new ArrayList<>();
        lista.add("winget install AnyDesk.AnyDesk");
        lista.add("winget install Discord.Discord");
        lista.add("winget install Oracle.JavaRuntimeEnvironment");
        lista.add("winget install Google.Chrome");
        lista.add("winget install Google.GoogleDrive");

        for (String listaAplicativos : lista) {
            executar(listaAplicativos);
        }
    }

    private static void executar(String comando) throws Exception {
        try {
            ProcessBuilder processo = new ProcessBuilder("cmd.exe", "/c" + comando);
            processo.inheritIO();
            processo.start().waitFor();
            if (comando.startsWith("winget install")) {
                System.out.println("Instalação concluída - " + comando.replace("winget install ", "") + ".\n");
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar instalar o aplicativo" + comando.replace("winget install ", comando)
                    + e.getMessage());
        }
    }
}
