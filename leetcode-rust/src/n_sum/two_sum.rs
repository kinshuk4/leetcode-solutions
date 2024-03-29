use std::collections::HashMap;

struct Solution;

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let mut value_to_position: HashMap<i32, i32> = HashMap::new();
        for (index, value) in nums.iter().enumerate() {
            let remaining = target - *value;
            if let Some(first) = value_to_position.get(&remaining) {
                return vec![*first as i32, index as i32];
            }
            value_to_position.insert(*value, index as i32);
        }

        vec![]
    }
}

#[cfg(test)]
mod tests {
    use super::Solution;

    #[test]
    fn test_0() {
        assert_eq!(Solution::two_sum(vec![2, 7, 11, 15], 9), vec![0, 1]);
    }
}
