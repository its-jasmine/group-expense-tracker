const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || "http://localhost:8080/api"; // TODO use environment variable

async function request(path, options = {}) {
  const response = await fetch(`${API_BASE_URL}${path}`, {
    headers: { "Content-Type": "application/json", ...(options.headers || {}) },
    credentials: "include",
    ...options,
  });
  if (!response.ok) {
    const text = await response.text();
    throw new Error(`HTTP ${response.status}: ${text}`);
  }
  const contentType = response.headers.get("content-type") || "";
  return contentType.includes("application/json") ? response.json() : response.text();
}

export const api = {
  // Members
  getMembers: () => request("/members"),
  createMember: (member) => request("/members", { method: "POST", body: JSON.stringify(member) }),
  deleteMember: (id) => request(`/members/${id}`, { method: "DELETE" }),

  // Expenses
  getExpenses: () => request("/expenses"),
  createExpense: (expense) => request("/expenses", { method: "POST", body: JSON.stringify(expense) }),
  deleteExpense: (id) => request(`/expenses/${id}`, { method: "DELETE" }),

  // Summary (debts, balances, totals)
  getSummary: () => request("/summary"),
};

export default api;


