package me.earth.headlessmc.runtime.commands;

import me.earth.headlessmc.api.command.CommandException;
import me.earth.headlessmc.command.ParseUtil;
import me.earth.headlessmc.runtime.Runtime;

public class PopCommand extends AbstractRuntimeCommand {
    public PopCommand(Runtime ctx) {
        super(ctx, "pop", "Pops Objects of the memory.");
        args.put("<addr>", "The address to set to null.");
        args.put("<target>", "Optional, the object will be moved here.");
    }

    @Override
    public void execute(String... args) throws CommandException {
        if (args.length < 2) {
            throw new CommandException("Please specify an address!");
        }

        int address = ParseUtil.parseI(args[1]);
        if (args.length > 2) {
            int to = ParseUtil.parseI(args[2]);
            ctx.log("Moving " + address + " to " + to + ".");
            ctx.getVm().set(ctx.getVm().pop(address), to);
        } else {
            ctx.log("Popping " + address + ".");
            ctx.getVm().pop(address);
        }
    }

}
