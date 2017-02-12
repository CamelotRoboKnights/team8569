package org.firstinspires.ftc.teamcode.team.Merlin1617.Merlin2_2;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ForwardAndTurn", group = "Merlin2")//This NEEDS to be changed tp the name of the code
//@Disabled //Uncomment this if it is not wanted on the phone
public class ForwardAndTurn extends Merlin2TeleOpMethods {
    private String CurrentCase = "AllSet";
    private double StartTime;

    public void init(){//This only runs once
        super.init();//Initializing everything needed
    }

    @Override
    public void init_loop(){StartTime = System.currentTimeMillis();//Tis runs many time during the init phase
    }

    @Override
    public void start(){}//This runs when the start button is pressed

    @Override
    public void loop(){//This runs while opmode is active
        super.math();


    }
    @Override
    public void stop(){
    }


}
