Aergo Community 구동 방법
1. ${AERGO_COMMUNITY_HOME}/run.sh를 실행하여 Aergo를 구동합니다.
2. Atom으로 ${AERGO_COMMUNITY_HOME}/contract/db-1.2.0.lua를 배포한 뒤, Contract Address를 application.yaml에 기입합니다.
   - node : localhost:7845
   - keystore : ${AERGO_COMMUNITY_HOME}/blockchain/jdbc
   - password : jdbc
3. 파일 업로드를 위해 파일을 저장할 디렉터리를 생성합니다.
4. application.yaml의 file.upload.path에 파일을 저장할 디렉터리 경로를 기입합니다.
5. ${AERGO_COMMUNITY_API}/ServerLauncher를 실행합니다. 