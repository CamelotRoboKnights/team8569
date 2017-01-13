/*
 * This was written when trying to write the field oriented drive, it was used
 * to do mass amounts of math for us
 */

package org.firstinspires.ftc.teamcode.team.Other;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Math", group = "Other")
@Disabled
public class LongMath extends LinearOpMode {

    double versionNumber = 1;
    double pi = 3.141592653589;
    /* Declare OpMode members. */


    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            //Angle 0 point (0,1)
            double OX = 0;
            double OY = 1;
            double NX;
            double NY;
            double Angle = 0;
            double M1;
            double M2;
            double M3;
            double M4;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);

            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("001 =", M1);
            telemetry.addData("002 =", M2);
            telemetry.addData("003 =", M3);
            telemetry.addData("004 =", M4);

            telemetry.addData(" ANGLE 0, Point (0,1)  ", versionNumber );
            telemetry.addData("X01", NX);
            telemetry.addData("Y01", NY);



            //Angle 0 point (1,0)
            OX = 1;
            OY = 0;
            Angle = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);

            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("011 =", M1);
            telemetry.addData("012 =", M2);
            telemetry.addData("013 =", M3);
            telemetry.addData("014 =", M4);

            telemetry.addData(" ANGLE 0, Point (1,0)  ", versionNumber );
            telemetry.addData("X02", NX);
            telemetry.addData("Y02", NY);
            //Angle 0 point (0,-1)
            OX = 0;
            OY = -1;
            Angle = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("021 =", M1);
            telemetry.addData("022 =", M2);
            telemetry.addData("023 =", M3);
            telemetry.addData("024 =", M4);
            telemetry.addData(" ANGLE 0, Point (0,-1)  ", versionNumber );
            telemetry.addData("X03", NX);
            telemetry.addData("Y03", NY);
            //Angle 0 point (-1,0)
            OX = -1;
            OY = 0;
            Angle = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("031 =", M1);
            telemetry.addData("032 =", M2);
            telemetry.addData("033 =", M3);
            telemetry.addData("034 =", M4);
            telemetry.addData(" ANGLE 0, Point (-1,0)  ", versionNumber );
            telemetry.addData("X04", NX);
            telemetry.addData("Y04", NY);





















            //Angle 1 point (0,1)
            OX = 0;
            OY = 1;
            Angle = 90 * pi/180 ;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("101 =", M1);
            telemetry.addData("102 =", M2);
            telemetry.addData("103 =", M3);
            telemetry.addData("104 =", M4);
            telemetry.addData(" ANGLE 1, Point (0,1)  ", versionNumber );
            telemetry.addData("X11", NX);
            telemetry.addData("Y11", NY);



            //Angle 1 point (1,0)
            OX = 1;
            OY = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("111 =", M1);
            telemetry.addData("112 =", M2);
            telemetry.addData("113 =", M3);
            telemetry.addData("114 =", M4);
            telemetry.addData(" ANGLE 1, Point (1,0)  ", versionNumber );
            telemetry.addData("X12", NX);
            telemetry.addData("Y12", NY);

            //Angle 1 point (0,-1)
            OX = 0;
            OY = -1;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("121 =", M1);
            telemetry.addData("122 =", M2);
            telemetry.addData("123 =", M3);
            telemetry.addData("124 =", M4);
            telemetry.addData(" ANGLE 1, Point (0,-1)  ", versionNumber );
            telemetry.addData("X13", NX);
            telemetry.addData("Y13", NY);

            //Angle 1 point (-1,0)
            OX = -1;
            OY = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("131 =", M1);
            telemetry.addData("132 =", M2);
            telemetry.addData("133 =", M3);
            telemetry.addData("134 =", M4);
            telemetry.addData(" ANGLE 1, Point (-1,0)  ", versionNumber );
            telemetry.addData("X14", NX);
            telemetry.addData("Y14", NY);














            //Angle 2 point (0,1)
            OX = 0;
            OY = 1;
            Angle = 180 * pi/180 ;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("201 =", M1);
            telemetry.addData("202 =", M2);
            telemetry.addData("203 =", M3);
            telemetry.addData("204 =", M4);
            telemetry.addData(" ANGLE 2, Point (0,1)  ", versionNumber );
            telemetry.addData("X21", NX);
            telemetry.addData("Y21", NY);



            //Angle 2 point (1,0)
            OX = 1;
            OY = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("211 =", M1);
            telemetry.addData("212 =", M2);
            telemetry.addData("213 =", M3);
            telemetry.addData("214 =", M4);
            telemetry.addData(" ANGLE 2, Point (1,0)  ", versionNumber );
            telemetry.addData("X22", NX);
            telemetry.addData("Y22", NY);

            //Angle 1 point (0,-1)
            OX = 0;
            OY = -1;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("221 =", M1);
            telemetry.addData("222 =", M2);
            telemetry.addData("223 =", M3);
            telemetry.addData("224 =", M4);
            telemetry.addData(" ANGLE 2, Point (0,-1)  ", versionNumber );
            telemetry.addData("X23", NX);
            telemetry.addData("Y23", NY);

            //Angle 1 point (-1,0)
            OX = -1;
            OY = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("231 =", M1);
            telemetry.addData("232 =", M2);
            telemetry.addData("233 =", M3);
            telemetry.addData("234 =", M4);
            telemetry.addData(" ANGLE 2, Point (-1,0)  ", versionNumber );
            telemetry.addData("X24", NX);
            telemetry.addData("Y24", NY);















            //Angle 1 point (0,1)
            OX = 0;
            OY = 1;
            Angle = 270 * 180/pi ;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("301 =", M1);
            telemetry.addData("302 =", M2);
            telemetry.addData("303 =", M3);
            telemetry.addData("304 =", M4);
            telemetry.addData(" ANGLE 3, Point (0,1)  ", versionNumber );
            telemetry.addData("X31", NX);
            telemetry.addData("Y31", NY);



            //Angle 1 point (1,0)
            OX = 1;
            OY = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("311 =", M1);
            telemetry.addData("312 =", M2);
            telemetry.addData("313 =", M3);
            telemetry.addData("314 =", M4);
            telemetry.addData(" ANGLE 3, Point (1,0)  ", versionNumber );
            telemetry.addData("X32", NX);
            telemetry.addData("Y32", NY);

            //Angle 1 point (0,-1)
            OX = 0;
            OY = -1;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("321 =", M1);
            telemetry.addData("322 =", M2);
            telemetry.addData("323 =", M3);
            telemetry.addData("324 =", M4);
            telemetry.addData(" ANGLE 3, Point (0,-1)  ", versionNumber );
            telemetry.addData("X33", NX);
            telemetry.addData("Y33", NY);

            //Angle 1 point (-1,0)
            OX = -1;
            OY = 0;

            NX = OY * Math.cos(Angle) + OX * Math.sin(Angle);
            NY = -OY * Math.sin(Angle) + OX * Math.cos(Angle);
            M1 = NY + NX;
            M2 = NY - NX;
            M3 = NY - NX;
            M4 = NY + NX;
            telemetry.addData("331 =", M1);
            telemetry.addData("332 =", M2);
            telemetry.addData("333 =", M3);
            telemetry.addData("334 =", M4);
            telemetry.addData(" ANGLE 3, Point (-1,0)  ", versionNumber );
            telemetry.addData("X34", NX);
            telemetry.addData("Y34", NY);
            telemetry.update();





        }
    }



}
