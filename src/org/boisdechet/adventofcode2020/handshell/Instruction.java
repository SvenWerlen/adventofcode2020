package org.boisdechet.adventofcode2020.handshell;

import java.util.HashMap;
import java.util.Map;

public class Instruction {

    public static final int OPER_NOP = 0;
    public static final int OPER_ACC = 1;
    public static final int OPER_JMP = 2;

    public static Map<Integer, String> INSTR = Map.of(OPER_NOP, "nop", OPER_ACC, "acc", OPER_JMP, "jmp");

    private int operation;
    private int argument;

    public Instruction(String instrAsString) {
        String[] val = instrAsString.split(" ");
        if(val.length != 2) {
            throw new IllegalStateException("Invalid instruction : " + instrAsString);
        }
        this.argument = Integer.parseInt(val[1]);
        switch(val[0]) {
            case "nop": this.operation = OPER_NOP; break;
            case "acc": this.operation = OPER_ACC; break;
            case "jmp": this.operation = OPER_JMP; break;
            default: throw new IllegalStateException("Invalid operation : " + val[0]);
        }
    }

    public Instruction(int operation, int argument) {
        this.operation = operation;
        this.argument = argument;
    }

    public int getOperation() {
        return operation;
    }

    public int getArgument() {
        return argument;
    }

    public boolean fixCorrupted() {
        if(this.operation == OPER_ACC) {
            return false;
        } else if(this.operation == OPER_NOP) {
            this.operation = OPER_JMP;
            return true;
        } else if(this.operation == OPER_JMP) {
            this.operation = OPER_NOP;
            return true;
        } else {
            throw new IllegalStateException("Invalid operation to fix : " + INSTR.get(this.operation));
        }
    }

    @Override
    public String toString() {
        return String.format("%s %+d", INSTR.get(this.operation), this.argument);
    }
}
