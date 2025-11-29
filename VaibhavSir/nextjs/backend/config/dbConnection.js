import mongoose from "mongoose";

async function connectDB() {
  try {
    await mongoose.connect("mongodb://127.0.0.1:27017/nextjsClass"); // no options needed
    console.log("MongoDB connected successfully");
  } catch (err) {
    console.error("MongoDB connection error:", err);
  }
}

connectDB();

mongoose.connection.once("open", () => {
  console.log("DB connection is ready!");
});
mongoose.connection.on("error", (err) => {
  console.error("DB connection error:", err);
});
