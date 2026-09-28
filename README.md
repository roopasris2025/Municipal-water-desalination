# Municipal Water Desalination & Brine Mineral Extraction Management System

A simple academic Spring Boot + Thymeleaf project simulating a municipal
seawater desalination plant: RO production, brine mineral recovery, sales to
municipal authorities, membrane purchasing, invoicing/billing, budgeting and
reporting. No real sensors or payment gateways — everything is entered
manually through the UI.

## Tech Stack
Java 17, Spring Boot 3, Spring MVC, Spring Data JPA, MySQL, Thymeleaf, Bootstrap 5.

## 1. Prerequisites
- JDK 17+
- Maven 3.8+ (or use your IDE's built-in Maven)
- MySQL 8 running locally

## 2. Database setup
You don't need to create tables manually — Hibernate (`ddl-auto=update`) creates
them for you. You only need the database itself:

```sql
CREATE DATABASE desalination_db;
```

Copy `src/main/resources/application-example.properties` to
`src/main/resources/application.properties`. Set the `DB_USERNAME` and
`DB_PASSWORD` environment variables to match your local MySQL account. The local
`application.properties` file is ignored by Git so database credentials are not
published.

For PowerShell, set them before starting the app:

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "your-mysql-password"
mvn spring-boot:run
```

## 3. Run the project

```bash
mvn spring-boot:run
```

Or import the folder as a Maven project in your IDE (IntelliJ / Eclipse / VS Code)
and run `DesalinationApplication.java`.

The app starts at: **http://localhost:8080**

## 4. What happens on first run
`DataSeeder` (in `config/`) inserts demo data automatically the first time the
app runs against an empty database:
- RO Train 1 (capacity 10,000 m3/day, pressure limit 60 bar)
- One Municipal Authority, one Supplier, one Mineral Buyer
- The 3 products: Potable Desalinated Water, Industrial Grade Salt Byproduct,
  RO Membrane Replacement
- Two telemetry readings: a normal one (55 bar) and a high-pressure one
  (72 bar) — the second one immediately creates a **HIGH PRESSURE ALERT**,
  visible on the Dashboard.

## 5. Suggested demo walkthrough
1. Open the **Dashboard** — see water produced/sold, revenue, expenses, budget
   usage and the seeded HIGH PRESSURE alert.
2. Go to **Operations → Telemetry → Submit Reading**, pick RO Train 1, enter a
   pressure above 60 bar, submit — watch a new alert appear on the Dashboard.
3. Go to **Master Data → Products / Suppliers / Water Authorities** to see
   simple Add/Edit/Delete CRUD screens.
4. Go to **Transactions → Purchase Orders → New PO**, create a PO for the
   Membrane product, then go to **Vendor Bills → New Bill**, link it to the
   PO, then click **Pay** — the bill turns PAID and the amount is added to
   expenses (check Dashboard/Reports).
5. Go to **Transactions → Sales Orders → New Sales Order**, sell water to the
   Municipal Authority, then **Customer Invoices → New Invoice**, link it,
   then click **Receive Payment** — the invoice turns PAID and the amount is
   added to revenue.
6. Go to **Finance → Budget → Add Budget**, enter a planned amount and an
   actual amount, and see Remaining Amount / Used % calculated automatically.
7. Go to **Finance → Reports** to see the combined Operational, Profit & Loss
   and Budget reports.

## 6. REST API (for Postman testing)
All CRUD APIs live under `/api/desal/...`, e.g.:
- `GET/POST /api/desal/trains`, `GET/PUT/DELETE /api/desal/trains/{id}`
- `GET/POST /api/desal/authorities`
- `GET/POST /api/desal/suppliers`
- `GET/POST /api/desal/mineral-buyers`
- `GET/POST /api/desal/products`
- `GET/POST /api/desal/telemetry`
- `GET/POST /api/desal/purchase-orders`
- `GET/POST /api/desal/vendor-bills`
- `GET/POST /api/desal/sales-orders`
- `GET/POST /api/desal/invoices`
- `GET/POST /api/desal/budgets`

These return/accept JSON and are separate from the Thymeleaf web pages
(different controller classes: `@RestController` vs `@Controller`).

## 7. Project structure
```
src/main/java/com/example/desalination
  ├── entity          -> @Entity classes (JPA tables)
  ├── repository      -> Spring Data JPA repositories
  ├── service         -> business logic (calculations, alerts, payments)
  ├── controller      -> @Controller classes (Thymeleaf web pages)
  ├── restcontroller  -> @RestController classes (JSON REST APIs)
  ├── config          -> DataSeeder (demo data on startup)
  └── DesalinationApplication.java

src/main/resources
  ├── templates       -> Thymeleaf HTML pages (one folder per module)
  ├── static/css      -> custom styling on top of Bootstrap
  └── application.properties
```
