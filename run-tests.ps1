# PowerShell script to run tests
echo "Running Maven tests..."
mvn clean test
if ($LASTEXITCODE -ne 0) { Write-Host "Tests failed (exit code $LASTEXITCODE)"; exit $LASTEXITCODE }
Write-Host "Tests finished. Reports in target/extent-reports"

