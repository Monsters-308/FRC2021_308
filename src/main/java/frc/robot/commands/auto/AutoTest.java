package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.drive.DriveAimStop;
import frc.robot.commands.drive.DriveDistance;
import frc.robot.commands.drive.DriveTurn;
//import frc.robot.commands.drive.DriveWideTurn;
import frc.robot.commands.hopper.ForwardHopper;
import frc.robot.commands.indexer.ForwardIndexer;
import frc.robot.commands.intake.ForwardIntake;
import frc.robot.commands.shooter.ShootSpeed;
import frc.robot.subsystems.DriveSubsystem;

/**
 * A complex auto command that starts the shooter drives forward, Backward, Forward intakes 2 balls Aims and shoots .
 */
public class AutoTest extends SequentialCommandGroup {
  /**
   * Creates a new AutoNearTrench.
   *
   * @param drive The drive subsystem this command will run on
   */

  public AutoTest(DriveSubsystem drive) {
    addCommands(
          new SequentialCommandGroup
          (
            new DriveDistance(20.0,0.3,drive),
            new DriveTurn(90,0.4, drive),
            new DriveDistance(20.0,0.3,drive),
            new DriveTurn(90,0.4, drive),
            new DriveDistance(20.0,0.3,drive),
            new DriveTurn(90,0.4, drive),
            new DriveDistance(20.0,0.3,drive),
            new DriveTurn(90,0.4 , drive) 
          )
              
    );
  }

}
