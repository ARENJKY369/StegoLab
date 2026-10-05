# StegLab

StegLab is a focused, ephemeral workspace for steganography, steganalysis, encryption, protocol simulation, and security education. It uses a Next.js 14 frontend and a Java 21 Spring Boot 3 backend built with Maven. No accounts or database are required.

## Run with Docker

```bash
docker compose up --build
```

Open [http://localhost:3000](http://localhost:3000). API documentation is at [http://localhost:8000/docs](http://localhost:8000/docs).

Stop the stack with:

```bash
docker compose down
```

## Run locally

Backend:

```bash
cd backend
mvn spring-boot:run
```

Frontend, in a second terminal:

```bash
cd frontend
npm install
npm run dev
```

## Self-test

With the backend running:

```bash
python scripts/self_test.py
```

The smoke test exercises health, capabilities, text, network, crypto, utilities, analysis, and audio API surfaces. The image and reporting endpoints require meaningful image/result fixtures and are best tested through their UI or `/docs`.

## Pages

- `/` — workspace home
- `/image` — PNG/BMP LSB embedding and extraction
- `/audio` — PCM WAV LSB embedding and extraction
- `/text` — zero-width encoding and invisible-character scanning
- `/network` — DNS, HTTP, packet, and timing simulations
- `/analysis` — structural risk scan
- `/security` — AES-256-GCM encryption layer
- `/utilities` — Base64, hex, binary, and entropy tools
- `/reports` — JSON and PDF report export
- `/learn` — interactive LSB explainer and glossary

## API endpoints

- `GET /api/health` — service health
- `GET /api/capabilities` — supported module summary
- `POST /api/image/capacity` — image capacity calculation
- `POST /api/image/embed` — image LSB embedding
- `POST /api/image/extract` — image payload extraction
- `POST /api/audio/capacity` — WAV capacity calculation
- `POST /api/audio/embed` — WAV LSB embedding
- `POST /api/audio/extract` — WAV payload extraction
- `POST /api/text/encode/zero-width` — invisible text encoding
- `POST /api/text/decode/zero-width` — invisible text decoding
- `POST /api/text/scan` — invisible character scan
- `POST /api/network/dns` — DNS simulation
- `POST /api/network/headers` — HTTP header simulation
- `POST /api/network/packet` — packet field simulation
- `POST /api/network/timing` — timing channel simulation
- `POST /api/analysis/scan` — structural file scan
- `POST /api/crypto/encrypt` — AES-256-GCM encryption
- `POST /api/crypto/decrypt` — AES-256-GCM decryption
- `POST /api/utilities/codec` — Base64, hex, and binary conversion
- `POST /api/utilities/entropy` — entropy calculation
- `POST /api/reports/json` — JSON report download
- `POST /api/reports/pdf` — PDF report download

## Security and retention

Files are processed in memory or request-scoped temporary data and are not retained by the application. No file content is logged. Upload-related feature endpoints enforce the 50 MB design limit, and CPU-facing routes use rate limiting. Production deployments should place the Compose stack behind a TLS reverse proxy and set a production-specific origin allowlist. HTTPS termination is intentionally deployment-specific rather than bundled into the development Compose file.

## Third-party libraries

- Next.js / React / TypeScript — frontend application
- Tailwind CSS — custom theme and responsive styling
- Framer Motion — available for interaction animation work
- Radix UI — available for accessible primitives
- FastAPI / Uvicorn — backend API
- SlowAPI — request rate limiting
- Pillow — image validation and pixel processing
- cryptography — AES-GCM and PBKDF2
- ReportLab — PDF report generation

## Known limitations

This staged build does not yet include DCT/F5, palette embedding, EXIF/IPTC/XMP authoring, advanced audio phase/echo/spread-spectrum methods, DOCX/XLSX object manipulation, full chi-square/RS/sample-pairs steganalysis, RSA key exchange, FLAC conversion, batch folder scanning, shareable scan links, or SSIM/PSNR computation. The implemented modules are deliberately conservative foundations for those features.
