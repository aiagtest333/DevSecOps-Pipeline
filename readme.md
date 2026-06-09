# DevSecOps Pipeline for Java/Maven Applications

A complete DevSecOps CI/CD pipeline for Java/Maven applications that integrates security checks throughout the software delivery lifecycle.

## Features

### Security Scanning
- 🔍 Scans Git history for leaked credentials using **TruffleHog**
- 🛡️ Performs static application security testing (SAST) using **Semgrep**
- 📦 Generates a Software Bill of Materials (SBOM) using **CycloneDX**
- 🚨 Scans project dependencies for known vulnerabilities (CVEs) using **Trivy**
- 🐳 Scans container images for vulnerabilities using **Trivy**

### Supply Chain Security
- ✍️ Cryptographically signs container images using **Cosign**
- 📄 Attests generated SBOMs to signed container images
- ✅ Verifies image signatures before deployment

### CI/CD Automation
- ⚙️ Builds Java applications using Maven
- 🐳 Builds and pushes Docker images to GitHub Container Registry (GHCR)
- 🚫 Enforces security gates between stages
- 🔒 Prevents deployment when any security check fails

---

## Pipeline Architecture

```text
┌─────────────────┐
│ Source Code     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ TruffleHog      │
│ Secret Scan     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Semgrep         │
│ SAST Scan       │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Maven Build     │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ CycloneDX       │
│ SBOM Generation │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Trivy           │
│ Dependency Scan │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Docker Build    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Push to GHCR    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Trivy           │
│ Image Scan      │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Cosign Sign     │
│ & Attestation   │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Signature       │
│ Verification    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ Deployment      │
└─────────────────┘
```

---

## Tools Used

| Tool | Purpose |
|--------|---------|
| TruffleHog | Secret and credential detection |
| Semgrep | Static Application Security Testing (SAST) |
| CycloneDX | SBOM generation |
| Trivy | Dependency and container vulnerability scanning |
| Maven | Java build automation |
| Docker | Containerization |
| GHCR | Container image registry |
| Cosign | Image signing and attestation |

---

## Workflow Stages

### 1. Secret Scanning
The pipeline scans the complete Git history using TruffleHog to detect:
- API keys
- Passwords
- Access tokens
- Cloud credentials
- Other sensitive information

### 2. Static Code Analysis
Semgrep scans the source code for:
- Security vulnerabilities
- Misconfigurations
- Insecure coding practices
- OWASP Top 10 issues

### 3. Build and Test
The Java application is compiled and packaged using Maven.

### 4. SBOM Generation
CycloneDX generates a Software Bill of Materials containing:
- Direct dependencies
- Transitive dependencies
- Package versions
- Component metadata

### 5. Dependency Vulnerability Scan
Trivy analyzes project dependencies against known CVE databases.

### 6. Container Build and Push
The application is containerized and pushed to GitHub Container Registry (GHCR).

### 7. Container Image Scan
Trivy scans the container image for:
- Operating system vulnerabilities
- Package vulnerabilities
- Critical and high-severity CVEs

### 8. Signing and Attestation
Cosign:
- Signs the container image
- Attaches SBOM attestations
- Ensures artifact integrity

### 9. Verification
Before deployment:
- Image signatures are verified
- SBOM attestations are validated
- Deployment proceeds only if verification succeeds

---

## Security Gates

The pipeline follows a **fail-fast** approach.

If any stage fails:
- Secret detection
- SAST scan
- Dependency scan
- Image scan
- Signature verification

The workflow immediately stops and prevents deployment.

---

## Benefits

- Early vulnerability detection
- Automated security enforcement
- Supply chain security
- SBOM compliance
- Signed container images
- Secure deployment validation
- Reduced risk of credential leakage
- Continuous security monitoring