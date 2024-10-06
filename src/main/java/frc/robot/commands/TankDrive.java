// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends Command {
  private final DriveTrain driveTrain;
  private final Supplier<Double> speedFuncLeft;

  private final Supplier<Double> speedFuncRight;

  public TankDrive(DriveTrain driveTrain, Supplier<Double> speedFuncLeft, Supplier<Double> speedFuncRight) {
    this.speedFuncLeft = speedFuncLeft;
    this.speedFuncRight = speedFuncRight;
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double left = speedFuncLeft.get();
    double right = speedFuncRight.get();

    driveTrain.turnLeftMotors(left);
    driveTrain.turnRightMotors(right);
  }

  @Override
  public void end(boolean interrupted) {
    driveTrain.turnLeftMotors(0);
    driveTrain.turnRightMotors(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
