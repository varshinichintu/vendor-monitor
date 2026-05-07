import { useEffect, useState } from "react";
import api from "../services/api";

export default function VendorList() {
  const [vendors, setVendors] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api.get("/vendors")
      .then((res) => {
        setVendors(res.data);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, []);

  if (loading) return <p>Loading...</p>;

  if (vendors.length === 0) return <p>No data found</p>;

  return (
    <table border="1">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
        </tr>
      </thead>
      <tbody>
        {vendors.map((v) => (
          <tr key={v.id}>
            <td>{v.id}</td>
            <td>{v.name}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}