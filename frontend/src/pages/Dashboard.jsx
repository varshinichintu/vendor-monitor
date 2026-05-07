import { useEffect, useState } from "react";
import api from "../services/api";
import { LineChart, Line, XAxis, YAxis } from "recharts";

export default function Dashboard() {
  const [stats, setStats] = useState(null);

  useEffect(() => {
    api.get("/stats").then((res) => setStats(res.data));
  }, []);

  if (!stats) return <p>Loading...</p>;

  return (
    <div style={{ padding: "20px" }}>

      {/* KPI CARDS */}
      <div style={{ display: "flex", gap: "10px", marginBottom: "20px" }}>
        <div>Vendors: {stats.totalVendors}</div>
        <div>Active: {stats.active}</div>
        <div>Inactive: {stats.inactive}</div>
        <div>Score Avg: {stats.avgScore}</div>
      </div>

      {/* CHART */}
      <LineChart width={500} height={300} data={stats.chart}>
        <XAxis dataKey="name" />
        <YAxis />
        <Line type="monotone" dataKey="value" />
      </LineChart>

    </div>
  );
}