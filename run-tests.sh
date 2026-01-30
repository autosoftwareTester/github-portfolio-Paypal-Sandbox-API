#!/usr/bin/env bash

echo "Running Maven tests..."
mvn clean test
rc=$?
if [ $rc -ne 0 ]; then
  echo "Tests failed (exit code $rc)"
  exit $rc
fi

echo "Tests finished. Reports in target/extent-reports"

