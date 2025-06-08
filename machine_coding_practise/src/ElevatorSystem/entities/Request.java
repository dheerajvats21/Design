package ElevatorSystem.entities;


public class Request {
    private Integer elevatorNumber;
    private int requestedFloor;
    private RequestType type;

    public Request(Integer elevator, int requestedFloor, RequestType type) {
        this.elevatorNumber = elevator;
        this.requestedFloor = requestedFloor;
        this.type = type;
    }


    public void setElevatorNumber(int elevatorNumber) {
        this.elevatorNumber = elevatorNumber;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public Integer getElevatorNumber() {
        return elevatorNumber;
    }

    public RequestType getType() {
        return type;
    }


    public int getRequestedFloor() {
        return requestedFloor;
    }


    public enum RequestType {
        INTERNAL,
        EXTERNAL
    }
}
