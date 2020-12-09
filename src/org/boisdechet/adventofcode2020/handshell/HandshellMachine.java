package org.boisdechet.adventofcode2020.handshell;

import java.util.List;

public class HandshellMachine {

    private List<Instruction> instructions;
    private int cur = 0;
    private long accu = 0;

    public HandshellMachine(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void next() {
        if(cur < 0 || cur >= instructions.size()) {
            throw new IllegalStateException("Illegal state : " + cur);
        }
        Instruction instr = instructions.get(cur);
        switch(instr.getOperation()) {
            case Instruction.OPER_NOP:
                cur++;
                break;
            case Instruction.OPER_ACC:
                accu += instr.getArgument();
                cur++;
                break;
            case Instruction.OPER_JMP:
                cur += instr.getArgument();
                break;
            default:
                throw new IllegalStateException("Invalid operation : " + instr.getOperation());
        }
    }

    public int getCur() { return this.cur; }
    public long getAccuValue() { return this.accu; }
    public Instruction getInstruction() { return instructions.get(cur); }
    public boolean isLastInstruction() { return this.cur == instructions.size() -1; }

    @Override
    public String toString() {
        return String.format("State (%d), Accu (%d)", this.cur, this.accu);
    }
}
