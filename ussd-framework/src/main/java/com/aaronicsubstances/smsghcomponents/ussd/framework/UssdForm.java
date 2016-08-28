/*
 *  (c)  2016. Aaronic Substances 
 */
package com.aaronicsubstances.smsghcomponents.ussd.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aaron
 */
public class UssdForm {
    private ArrayList<UssdInput> inputs;
    private int processingPosition;
    private String controller;
    private String action;
    private Map<String, String> data;
    
    public UssdForm(String action) {
        this(action, null);
    }

    public UssdForm(String action, String controller) {
        if (action == null) {
            throw new IllegalArgumentException("\"action\" argument cannot be "
                    + "null");
        }
        this.controller = controller;
        this.action = action;
        this.inputs = new ArrayList<UssdInput>();
        this.data = new HashMap<String, String>();
    }

    public ArrayList<UssdInput> getInputs() {
        return inputs;
    }

    public UssdForm inputs(ArrayList<UssdInput> inputs) {
        if (inputs == null) {
            throw new IllegalArgumentException("\"inputs\" argument cannot "
                    + "be null");
        }
        this.inputs = inputs;
        return this;
    }
    
    public UssdForm addInput(UssdInput input) {
        if (input == null) {
            throw new IllegalArgumentException("\"input\" argument cannot "
                    + "be null");
        }
        inputs.add(input);
        return this;
    }

    public int getProcessingPosition() {
        return processingPosition;
    }

    public UssdForm processingPosition(int processingPosition) {
        this.processingPosition = processingPosition;
        return this;
    }

    public String getController() {
        return controller;
    }

    public UssdForm controller(String controller) {
        this.controller = controller;
        return this;
    }

    public String getAction() {
        return action;
    }

    public UssdForm action(String action) {
        if (action == null) {
            throw new IllegalArgumentException("\"action\" argument cannot "
                    + "be null");
        }
        this.action = action;
        return this;
    }

    public Map<String, String> getData() {
        return data;
    }

    public UssdForm data(Map<String, String> data) {
        if (data == null) {
            throw new IllegalArgumentException("\"data\" argument cannot "
                    + "be null");
        }
        this.data = data;
        return this;
    }
    
    public String render() {
        if (processingPosition < 0 || processingPosition >=
                inputs.size()) {
            throw new RuntimeException(String.format("Invalid processing "
                    + "position (%d) for inputs of size %d",
                    processingPosition, inputs.size()));
        }
        UssdInput currentInput = inputs.get(processingPosition);
        if (currentInput == null) {
            throw new RuntimeException("Encountered null form input at index " +
                    processingPosition);
        }
        return currentInput.render();
    }
    
    // Used during testing.

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.inputs != null ? this.inputs.hashCode() : 0);
        hash = 37 * hash + this.processingPosition;
        hash = 37 * hash + (this.controller != null ? this.controller.hashCode() : 0);
        hash = 37 * hash + (this.action != null ? this.action.hashCode() : 0);
        hash = 37 * hash + (this.data != null ? this.data.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UssdForm other = (UssdForm) obj;
        if (this.processingPosition != other.processingPosition) {
            return false;
        }
        if ((this.controller == null) ? (other.controller != null) : !this.controller.equals(other.controller)) {
            return false;
        }
        if ((this.action == null) ? (other.action != null) : !this.action.equals(other.action)) {
            return false;
        }
        if (this.inputs != other.inputs && (this.inputs == null || !this.inputs.equals(other.inputs))) {
            return false;
        }
        if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UssdForm{" + "inputs=" + inputs + ", processingPosition=" + 
                processingPosition + ", controller=" + controller + 
                ", action=" + action + ", data=" + data + '}';
    }
    
}
