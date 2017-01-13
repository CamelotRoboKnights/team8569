//This is done being commented 17-1-13
/*
 * This is our new TeleOp and has the methods separate.
 */
package org.firstinspires.ftc.teamcode.team.Merlin2;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "RealOp", group = "Merlin2")//This NEEDS to be changed tp the name of the code
//@Disabled //Uncomment this if it is not wanted on the phone
public class Merlin2RealTeleOp extends Merlin2TeleOpMethods{

    public void init(){//This only runs once
        super.init();//Initializing everything needed
    }
    @Override
    public void init_loop(){//Tis runs many time during the init phase
    }
    @Override
    public void start(){}//This runs when the start button is pressed
    @Override
    public void loop(){//This runs while opmode is active

        super.driveChoice(super.LiftHeight);//The method that determines what drive program to use and gives the programs everything it needs
        super.collection();//Runs my collection method

        super.TargetEncoder = super.launchBall(TargetEncoder);//Run the launch ball method
        super.LiftHeight = liftCapBallLift();//Run the method that lifts the cap ball all the way when a button is pressed
        if(!super.ButtonPressed){//If that button is not pressed
            super.LiftHeight = super.lift();//Raise the cap ball lift manually
        }
        super.print(LiftHeight, TargetEncoder);//Print everything that I want

    }
    @Override
    public void stop(){}


}
