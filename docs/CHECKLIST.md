# TailPlanner – Full Engineering Build Order Checklist (Long-Term)

## Purpose

- Master reference for the entire project lifecycle  
- Single source of truth when design or implementation feels uncertain  
- UI, data, and behavior must strictly follow this order  

---

## Phase 0｜Engineering Foundations (0% → 5%)

### Define core entities (definition only, no UI)

- List  
- Plan  
- Todo  
- FocusSession  
- TimelineRecord  
- Review  
- Note  

### Core principles

- Timeline is the **single source of historical truth**  
- No module may write data directly, **except Timeline**

### Completion criteria

> Every feature must be able to answer one question:  
> **“Does this eventually write into Timeline?”**

---

## Phase 1｜List Entry & List System (5% → 15%)

### Scope

- List entry page  
- Bottom Sheet: select list type  
  - General list  
  - Goal-oriented list  
- Create list (name only)  
- Add items inside a list (date not required)

### Explicitly excluded

- List statistics  
- UI styling or polish  

### Completion criteria

> Can create → enter → add → check items successfully

---

## Phase 2｜Todo Timeline (15% → 30%)

### Scope

- Timeline-based Todo view  
- Three states:
  - Today  
  - Overdue  
  - Undated  
- Create Todo via “+” under date  
- Unified Todo edit page  
- Repeating Todo support  
- Todo can be linked to Plan  
- Complete Todo → write to Timeline  
- Un-complete Todo → do not write to Timeline  

### Explicitly excluded

- Today aggregation page  
- Todo statistics  

### Completion criteria

> Todo lifecycle is fully closed and consistent

---

## Phase 3｜Focus System (Single Task) (30% → 45%)

### Scope

- Three focus modes:
  - Count-up timer  
  - Countdown timer  
  - Pomodoro  
- Select focus target:
  - Plan  
  - Todo  
  - None  
- Focusing screen  
- End focus → input progress / notes  
- Write FocusSession to Timeline  
- Write progress to Plan history  

### Explicitly excluded

- Multitask focus  
- Blacklist / whitelist logic  

### Completion criteria

> One focus session equals exactly one TimelineRecord

---

## Phase 4｜Timeline (Core Freeze) (45% → 55%)

### Scope

- Daily / weekly / monthly views  
- Timeline item types:
  - Todo completion  
  - Focus session  
  - Manual entry  
- Editable fields:
  - Linked item (cannot be removed)  
  - Time range (cannot exceed current time)  
  - Progress / notes  

### 🔒 Freeze rules

- Timeline is the **only historical truth**  
- All other modules are **read-only consumers** of Timeline  

### Completion criteria

> Timeline alone can reconstruct an entire day’s activity

---

## Phase 5｜Plan Pages (Functional, Not Final UI) (55% → 70%)

### Scope

- Plan grouping  
- Plan detail page  
- Cumulative progress  
- Today’s progress  
- Linked Todos (read-only)  
- Focus history (from Timeline)  
- Plan completion records  

### Explicitly excluded

- Charts  
- Visual polish  
- Sharing features  

### 🔒 Freeze rule

- Plan never writes data directly; it only aggregates from Timeline  

### Completion criteria

> A Plan’s status is understandable without opening Timeline

---

## Phase 6｜Multitask Focus (70% → 75%)

### Scope

- Multitask focus (count-up only)  
- Select 2–5 focus targets  
- Switching target ends current FocusSession  
- Timeline records remain standard Focus entries  
- Unified progress / note input at the end  

### 🔒 Key principle

- Timeline is unaware of multitask mode  

### Completion criteria

> Multitask is supported without modifying Timeline structure

---

## Phase 7｜Today Page (75% → 80%)

### Scope

- Daily aggregation view  
- Todo count  
- Focus summary  
- Quick actions  

### Explicitly excluded

- Smart recommendations  
- AI summaries  

---

## Phase 8｜Records (Review & Notes) (80% → 88%)

### Scope

- Review entry  
- Notes entry  
- Create daily review (auto-aggregated)  
- Select list scope for review  
- Edit / search / filter records  

---

## Phase 9｜Statistics (88% → 95%)

### Scope

- Focus statistics  
- Plan progress  
- Habit trends  
- Todo completion trends  

---

## Phase 10｜UI & Experience Polish (95% → 100%)

### Scope

- Unified design language  
- Animations  
- Empty states  
- Sharing / export  

---
