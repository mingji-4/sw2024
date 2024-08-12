네이버 뉴스에서 특정 페이지의 이미지를 다운로드하는 Node.js 프로그램을 작성하려면, axios와 cheerio 모듈을 사용하여 웹페이지를 크롤링하고 이미지를 다운로드할 수 있습니다. 아래에 그 예제를 보여드리겠습니다.

1. 필요한 모듈 설치
먼저 필요한 모듈들을 설치해야 합니다.

bash
코드 복사
npm install axios cheerio fs path
axios: HTTP 요청을 보내기 위해 사용됩니다.
cheerio: 서버 사이드에서 HTML을 파싱하고 DOM을 조작하기 위해 사용됩니다.
fs: 파일 시스템에 접근하기 위해 사용됩니다.
path: 파일 경로를 처리하기 위해 사용됩니다.
2. Node.js 코드 작성
아래는 네이버 뉴스에서 인기 뉴스의 이미지를 크롤링하고 다운로드하는 코드입니다.

javascript
코드 복사
const axios = require('axios');
const cheerio = require('cheerio');
const fs = require('fs');
const path = require('path');

// 다운로드할 디렉토리 경로 설정
const downloadDir = path.join(__dirname, 'downloads');
if (!fs.existsSync(downloadDir)) {
    fs.mkdirSync(downloadDir);
}

// 네이버 뉴스 URL
const url = 'https://news.naver.com/main/ranking/popularDay.naver?mid=etc&sid1=111';

// 이미지 다운로드 함수
const downloadImage = async (imageUrl, filename) => {
    const filePath = path.join(downloadDir, filename);
    const writer = fs.createWriteStream(filePath);

    const response = await axios({
        url: imageUrl,
        method: 'GET',
        responseType: 'stream',
    });

    response.data.pipe(writer);

    return new Promise((resolve, reject) => {
        writer.on('finish', resolve);
        writer.on('error', reject);
    });
};

// 메인 크롤링 함수
const crawlImages = async () => {
    try {
        const response = await axios.get(url);
        const $ = cheerio.load(response.data);

        // 모든 이미지 태그를 선택하여 크롤링
        $('img').each(async (index, element) => {
            const imgSrc = $(element).attr('src');

            if (imgSrc && imgSrc.startsWith('https://')) {
                const imgFilename = path.basename(imgSrc);
                console.log(`Downloading image: ${imgFilename}`);

                try {
                    await downloadImage(imgSrc, imgFilename);
                    console.log(`Image downloaded: ${imgFilename}`);
                } catch (error) {
                    console.error(`Failed to download image: ${imgFilename}`, error);
                }
            }
        });
    } catch (error) {
        console.error('Error crawling images:', error);
    }
};

// 크롤링 실행
crawlImages();
3. 코드 설명
axios.get(url): 지정된 URL에 HTTP GET 요청을 보내 웹페이지의 HTML 데이터를 가져옵니다.
cheerio.load(response.data): cheerio를 사용하여 HTML을 파싱하고, jQuery처럼 DOM을 조작할 수 있습니다.
$('img').each(...): 모든 이미지 태그(img)를 순회하며 이미지를 다운로드합니다.
downloadImage 함수: 이미지의 URL을 받아 해당 이미지를 로컬에 다운로드합니다.
이미지 파일명: URL에서 파일명을 추출하여 사용합니다.
4. 실행
위 코드를 작성한 후 node 파일명.js로 실행하면 지정된 네이버 뉴스 페이지에서 이미지들을 다운로드할 수 있습니다.

이미지들은 downloads 폴더에 저장됩니다. 필요한 경우 코드에서 이미지를 저장할 경로나 크롤링할 다른 요소들을 자유롭게 수정할 수 있습니다.