const puppeteer = require('puppeteer');
const fs = require("fs");

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function startBrowser() {
    let browser;
    try {
        console.log("Opening the browser......");
        browser = await puppeteer.launch({
            headless: false,
            args: ["--disable-setuid-sandbox"],
            'ignoreHTTPSErrors': true
        });
    } catch (err) {
        console.log("Could not create a browser instance => : ", err);
    }
    return browser;
}

let courtCartClass = ".flex cursor-pointer flex-col gap-2 rounded-xl"
let loadMoreClass = ".flex flex-none items-center justify-center gap-2 rounded border border-solid border-transparent"
const imageClass = "img.rounded-lg";

while (courtCartClass.includes(" ")) {
    courtCartClass = courtCartClass.replace(" ", ".");
}

loadMoreClass = loadMoreClass.replace(" ", ".");

while (loadMoreClass.includes(" ")) {
    loadMoreClass = loadMoreClass.replace(" ", ".");
}

console.log("- courtCartClass:\t", courtCartClass);
console.log("- loadMoreClass:\t", loadMoreClass);

let urls = [];
let images = [];

async function run() {

    let browser = await startBrowser();
    let page = await browser.newPage();

    await page.goto("https://www.courtsite.my/explore/category/Pickleball/clc8tlc5bbt2p6ogmj1xm2h3a")

    const timeId = setInterval(
        async () => {
            console.log("....cho 3 giay");
            await page.waitForSelector(loadMoreClass);
            await page.click(loadMoreClass).then(() => {
                console.log(("clicked!"))
            });
        },
        3000
    );

    await sleep(11000);
    clearInterval(timeId);
    console.log("Kết thúc!");

    await page.waitForSelector(courtCartClass);

    let links = await page.evaluate((courtCartClass) => {
        let carts = document.querySelectorAll(courtCartClass);
        const links = [];
        console.log(courtCartClass)
        console.log(carts);
        for (const elm of carts) {
            links.push("https://www.courtsite.my" + elm.getAttribute("href"))
            console.log(elm.getAttribute("href"));
        }
        return links;
    }, courtCartClass);

    urls.push(...links)
    console.log(links);


    for (const url of urls) {
        await sleep(1000);
        console.log("...2 giay")
        await page.goto(url);
        console.log(`....go to '${url}'`);
        await sleep(2000);

        const imgRes = await page.evaluate((imageClass) => {
            const imgs = document.querySelectorAll(imageClass);
            let res = [];
            for (const img of imgs) {
                res.push(img.getAttribute("src").replace("3840", "640"));
                // console.log(`\t-> crawl image: \t`, img.getAttribute("src"));
            }
            return res;
        }, imageClass);

        images.push(imgRes);
        try {
            await fs.writeFile("D:\\Coding\\CourtReversationSystem\\src\\main\\resources\\crawl\\images.json", JSON.stringify(images), () => {});
            console.log("Saved!!!");

        } catch (e) {
            console.log(e);
        }
    }


}

(
    async () => {
        try {
            await run();
        } catch (e) {
            console.log(e);
        }
    }
)();




