package entities;

import java.io.Serializable;

public class Client implements Serializable, Comparable<Client> {
    private String name;
    private String code;
    private Boolean isConnected;
    private String address;

    public Client(){ }

    public Client(String name, String code, Boolean isConnected) {
        this.name = name;
        this.code = code;
        this.isConnected = isConnected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getConnected() {
        return isConnected;
    }

    public void setConnected(Boolean connected) {
        isConnected = connected;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int compareTo(Client client) {
        return this.code.compareTo(client.code);
    }
}
