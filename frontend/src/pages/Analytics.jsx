import { useEffect, useState } from "react";
import api from "../services/api";

import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  ResponsiveContainer
} from "recharts";

import "../styles/responsive.css";

export default function Analytics() {
  const [data, setData] = useState([]);
  const [period, setPeriod] = useState("weekly");

  useEffect(() => {
    api.get(`/analytics?period=${period}`)
      .then(res => setData(res.data));
  }, [period]);

  return (
    <div className="analytics-container">
      <h2>Analytics</h2>

      <select
        value={period}
        onChange={(e) => setPeriod(e.target.value)}
      >
        <option value="weekly">Weekly</option>
        <option value="monthly">Monthly</option>
      </select>

      <div className="chart-container">
        <ResponsiveContainer width="100%" height={300}>
          <LineChart data={data}>
            <XAxis dataKey="name" />
            <YAxis />
            <Line
              type="monotone"
              dataKey="value"
            />
          </LineChart>
        </ResponsiveContainer>
      </div>
    </div>
  );
}