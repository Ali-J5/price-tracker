import re
from playwright.sync_api import sync_playwright

def clean_price(price_str):
    cleaned = re.sub(r'[^\d.]', '', price_str)
    return float(cleaned) if cleaned else None

def scrape_amazon_price(url):
    print(f"Spinning up Chromium in the background to check: {url}")
    
    with sync_playwright() as p:
        # Turn HEADLESS back to TRUE so it runs invisibly
        browser = p.chromium.launch(headless=True)
        
        # Keep the stealth headers!
        page = browser.new_page(
            user_agent="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36",
            extra_http_headers={
                "Accept-Language": "en-US,en;q=0.9",
                "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
            }
        )
        
        try:
            page.goto(url, wait_until="domcontentloaded")
            price_locator = page.locator("span.a-price span.a-offscreen").first
            price_text = price_locator.inner_text(timeout=5000)
            final_price = clean_price(price_text)
            
            print(f"[SUCCESS] Parsed float value: {final_price}")
            return final_price
            
        except Exception as e:
            print(f"[ERROR] Could not extract price: {e}")
            return None
        finally:
            browser.close()

if __name__ == "__main__":
    test_url = "https://www.amazon.com/dp/B0BDHWDR12"
    scrape_amazon_price(test_url)