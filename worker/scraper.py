import re
import time
import random
import requests
from playwright.sync_api import sync_playwright

# API Configuration
API_BASE_URL = "http://localhost:8080/api/items"
API_KEY = "local-dev-secret-key-123"
HEADERS = {
    "X-API-KEY": API_KEY,
    "Content-Type": "application/json"
}

def clean_price(price_str):
    cleaned = re.sub(r'[^\d.]', '', price_str)
    return float(cleaned) if cleaned else None

def scrape_amazon_price(url, browser):
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
        return clean_price(price_text)
    except Exception as e:
        print(f"    [ERROR] Failed to extract price: {e}")
        return None
    finally:
        page.close()

def main():
    print("Fetching items from API...")
    try:
        response = requests.get(API_BASE_URL, headers=HEADERS)
        response.raise_for_status()
        items = response.json()
    except Exception as e:
        print(f"Failed to connect to API: {e}")
        return

    if not items:
        print("No items to track. Exiting.")
        return

    print(f"Found {len(items)} items. Spinning up stealth browser...\n")
    
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=True)
        
        for index, item in enumerate(items):
            item_id = item["id"]
            url = item["url"]
            
            print(f"--> Checking Item #{item_id}: {item['name']}")
            price = scrape_amazon_price(url, browser)
            
            if price:
                print(f"    [SUCCESS] Price: ${price}")
                try:
                    # Send the new price to the database
                    post_url = f"{API_BASE_URL}/{item_id}/prices"
                    requests.post(post_url, headers=HEADERS, json={"price": price})
                    print(f"    [SAVED] Price logged to database.")
                except Exception as e:
                    print(f"    [ERROR] Failed to save to API: {e}")
            
            # Politeness Delay: Sleep before checking the next item (skip after the last item)
            if index < len(items) - 1:
                sleep_time = random.uniform(4.0, 9.0)
                print(f"    [DELAY] Sleeping for {sleep_time:.2f} seconds to simulate human behavior...\n")
                time.sleep(sleep_time)
                
        browser.close()
        print("\nScraping run complete!")

if __name__ == "__main__":
    main()