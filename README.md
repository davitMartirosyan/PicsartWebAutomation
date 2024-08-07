![img_1.png](picsart.png)
# PicsArt Web Automation Framework

This repository contains a web automation framework for testing the PicsArt search functionality across different screen resolutions using Selenium and TestNG.

## Prerequisites

- Java Development Kit
- Maven
- Chrome browser

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/davitmartirosyan/PicsartWebAutomation.git
cd PicsartWebAutomation
```

## CSS Selectors and Attributes Used

- **Iframe Selector**: `[data-testid='com.picsart.social.search']`
- **Filter Button Selector**: `[data-testid='search-header-filter']`
- **Filter Section Selector**: `[data-testid='search-filter-root']`
- **License Section Selector**: `[data-testid='list']`
- **License Checkbox Selector**: `[data-testid='checkbox-item-root']`
- **License Checkbox Item Check Selector**: `[data-testid='checkbox-item-check']`
- **Content Root Selector**: `[data-testid='content-grid-root']`
- **Search Card Selector**: `[data-testid='search-card-root']`
- **Like Button Selector**: `[data-testid='like-button-root']`
- **Registration Overlay Selector**:` [data-testid='registration-overlay']`
- **Try Now Button Selector**: `[data-testid='try-now-button-root']`
