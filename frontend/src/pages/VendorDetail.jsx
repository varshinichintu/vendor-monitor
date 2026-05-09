import { useEffect, useState } from "react";
import api from "../services/api";

export default function VendorDetail({ id }) {
  const [vendor, setVendor] = useState(null);

  useEffect(() => {
    api.get(`/vendors/${id}`).then((res) => setVendor(res.data));
  }, [id]);

  if (!vendor) return <p>Loading...</p>;

  return (
    <div>
      <h2>{vendor.name}</h2>

      <p>Score: <span>{vendor.score}</span></p>

      <button>Edit</button>
      <button>Delete</button>
    </div>
  );
}