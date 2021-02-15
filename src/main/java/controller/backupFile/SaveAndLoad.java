package controller.backupFile;

public interface SaveAndLoad<X> {
    void save();
    X load();
}