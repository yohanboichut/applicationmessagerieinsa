package programme;


import facade.exceptions.InformationsNonConformesException;
import facade.exceptions.LoginDejaPrisException;
import rmistuff.MonService;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] argv) {
        try {
            Registry registry = LocateRegistry.getRegistry(10000);
            MonService stub = (MonService) registry.lookup(MonService.serviceName);
            stub.inscription("Yohan","Yohan");

        } catch (AccessException e) {
            e.printStackTrace();
        } catch (InformationsNonConformesException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (LoginDejaPrisException e) {
            e.printStackTrace();
        }
    }
}
