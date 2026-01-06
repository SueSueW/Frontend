# Project Retrospectives

This document records major decision points, invalidated assumptions, and key learnings during the early MVP exploration of the TailPlanner project.

The goal of these retrospectives is not to document features, but to capture **why certain paths were chosen or abandoned**, and how those decisions shaped the final MVP approach.

---

## Retrospective 1: Abandoning the “Learn Everything First” Web Path (2024.05)

### Problem at the Time
Before building a web-based version, the initial plan was to systematically study relevant technologies in advance, hoping to form a complete understanding before starting development.

However, the learning content gradually diverged from the actual problem being solved, making it difficult to translate theoretical knowledge into concrete implementation decisions.

---

### Decision Made
Chose a “learn first, build later” approach, assuming that understanding all possible implementation options would reduce future mistakes.

---

### What Actually Happened
The learning process became overly systematic and abstract.  
Without a concrete usage scenario as an anchor, it was difficult to determine which knowledge was immediately useful, resulting in low transfer from learning to actual development.

---

### Invalidated Assumption
> **“Learning everything first is an effective approach for exploratory projects.”**

---

### Conclusion
For exploratory and problem-driven projects, early hands-on implementation is more effective than attempting to build a complete theoretical foundation upfront.

---

### Impact on Next Stage
Abandoned the “finish learning before building” approach and began searching for implementation methods closer to real usage scenarios.

---

## Retrospective 2: Pivot from Web to Android App (2024.11)

### Problem at the Time
During web implementation attempts, significant limitations emerged in terms of usability and suitability for a single-user, high-frequency productivity tool.

---

### Decision Made
Initially chose a web-based solution due to perceived simplicity and cross-platform accessibility.

---

### What Actually Happened
In practical usage, the web version showed clear drawbacks: fragmented interactions, reduced responsiveness, and poor support for focused, long-term usage patterns.

---

### Invalidated Assumption
> **“A web application is sufficient for high-frequency, long-term personal execution tools.”**

---

### Conclusion
This problem domain is better addressed through a **native mobile application**, where interaction continuity, background capabilities, and usage habits align more closely with real needs.

---

### Impact on Next Stage
Formally shifted development direction to an **Android-native application**, discontinuing further web expansion.

---

## Retrospective 3: Correcting the System Construction Order (2025.10)

### Problem at the Time
After switching to Android development, the focus shifted toward improving user experience as early as possible.

---

### Decision Made
Started by designing and implementing UI and interaction flows first, planning to define the database structure afterward.

---

### What Actually Happened
Once UI implementation was complete, database design became difficult to reconcile with existing interactions.  
Core data structures could not adequately support the predefined UI logic, leading to extensive refactoring and stalled progress.

---

### Invalidated Assumption
> **“UI → interaction mapping → database” is a viable construction path for this type of system.**

---

### Conclusion
System-oriented applications must prioritize **data structure and fact modeling** before interaction and UI design.  
Additionally, architectural feasibility should be validated through a **minimal MVP**, rather than attempting to build a full system upfront.

---

### Impact on Next Stage
Abandoned UI-first development and adopted a database-first, minimal-path validation approach.

---

## Retrospective 4: Minimal MVP Path Successfully Validated (2026.01)

### Problem at the Time
To verify whether the database-first approach could support a complete execution and recording flow.

---

### Decision Made
Built core database structures and data mappings first, intentionally postponing UI considerations to focus on data correctness.

---

### What Actually Happened
The minimal execution path ran successfully.  
Data flows were clear and stable, and previous large-scale refactoring issues did not reoccur.

---

### Invalidated Assumption
None identified at this stage.

---

### Conclusion
The **“database → interaction mapping → UI”** construction order is both viable and efficient for this problem domain.

---

### Impact on Next Stage
This construction order was established as a foundational principle for future formal product development.

---
