package org.fasttrackit.onlineshop.transfer.user;

public class GetUsersRequest {

    private String partialFirstName;
    private String partialLastName;



    public String getPartialFirstName() {
        return partialFirstName;
    }

    public String getPartialLastName() {
        return partialLastName;
    }

    public void setPartialLastName(String partialLastName) {
        this.partialLastName = partialLastName;
    }

    @Override
    public String toString() {
        return "GetUsersRequest{" +
                "partialFirstName='" + partialFirstName + '\'' +
                ", partialLastName='" + partialLastName + '\'' +
                '}';
    }
}
